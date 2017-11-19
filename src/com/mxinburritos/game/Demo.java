package com.mxinburritos.game;

import java.applet.*;
import java.awt.*;
import java.util.*;
import java.util.List;

import sun.applet.AppletPanel;

public class Demo extends Applet {
	boolean uselessBoolean = true;
	boolean inventoryOpen = false;

	String command;

	private String[] types = { "class 1", "class 2", "class 3", "class 4", "class 5", "class 6" };
	private double[] typeHpStats = { 150, 100, 85, 112, 70 };
	private double[] typeDmgStats = { 0.75, 1.12, 1.00, 1.00, 1.25 };
	private int[] typeRangeStats = { 0, 0, 1, 0, 2 };
	private int[] typeSpdStats = { 4, 4, 4, 4, 3 };

	String name;
	String description;
	String type;
	
	Item none;
	
	int charInit = 0;

	Party party;
	
	Location start;
	Location currentLocation;

	TextField commandInput;
	TextArea displayOutput;
	Button buttonInput;
	Button inventoryButton;
	Panel inputPanel;
	Panel appletPanel;

	public Demo() {
		super();
	}

	public void init() {
		super.init();
		setBackground(Color.black);
		setBackground(Color.white);

		appletPanel = new Panel();

		BorderLayout b = new BorderLayout();
		appletPanel.setLayout(b);
		add(appletPanel);

		commandInput = new TextField(20);
		displayOutput = new TextArea(20, 60);
		buttonInput = new Button("Go");
		inventoryButton = new Button("Inventory");
		inputPanel = new Panel();

		inputPanel.add(commandInput);
		inputPanel.add(buttonInput);
		appletPanel.add("North", displayOutput);
		appletPanel.add("South", inputPanel);
		appletPanel.add("East", inventoryButton);

		party = new Party();

		start = new Location("starting place", "starting place");
		Location test = new Location("test", "test");

		start.addExit(new Exit(Exit.NORTH, test));

		displayOutput.append("\nWhat is your name?\n");

	}
	
	private void showLocation()
	{
		displayOutput.appendText("\n" + currentLocation.getTitle() + "\n");
		displayOutput.appendText( "\n" );

		displayOutput.appendText( currentLocation.getDescription()+ "\n");
		List charactersInCurrentLocation = new ArrayList();
		for(Object itemObj : currentLocation.characters){
			
		}
		displayOutput.appendText("\nAvailable exits: \n");
		for(Enumeration e = currentLocation.getExits().elements(); e.hasMoreElements();)
		{
			Exit an_exit = (Exit) e.nextElement();
			displayOutput.appendText(an_exit + "\n");
		}
	}
	
	public boolean action(Event evt, Object focus) {
		String command;

		if (evt.target.equals(inventoryButton)) {//if the inventory button is clicked
			if (charInit < 2) { //cannot open inventory while making character
				displayOutput.append("\nError : You can't access your inventory right now.\n");
				return true; //event handled
			} else {
				inventoryOpen = showInventory();
				return inventoryOpen; //event handled / not handled (depends on whether showInventory was successful or not)
			}

		}
		if (!evt.target.equals(inventoryButton)) {
			inventoryOpen = false; //if any button but inventoryButton was pressed, inventory is not open
		}

		if (evt.target.equals(buttonInput)) {//if the go button is clicked
			command = commandInput.getText();

			if (command.length() == 0) {//if the input is empty
				return true;//event handled
			}

			if (charInit == 1) {//if choosing class
				command = command.toUpperCase();//for comparison
			}
			if (charInit < 2) {//if making character (name is already done) ask about class
				if (ask(command)) {
					return true; //if successful, even handled. if not, continue
				}
				displayOutput.appendText("\nHuh? That doesn't sound right!\n"); //user input is not one of the choices
			}
			
			if(!(charInit < 2)){
				command = command.toUpperCase();
				for (Enumeration e = currentLocation.getExits().elements(); e.hasMoreElements();){

					Exit an_exit = (Exit) e.nextElement();

					if ((an_exit.getDirectionName().compareTo(command) == 0) || (an_exit.getShortDirectionName().compareTo(command) == 0 )){
						currentLocation = an_exit.getLeadsTo();
						showLocation();
						commandInput.setText (new String());
						return true;
					}				
				}
			}
			
			displayOutput.appendText("\nHuh? That doesn't sound right!\n"); //user input is not one of the choices
			
			commandInput.setText(new String());//clear input box

			return true;//event handled
		} else
			return false;//event not handled
	}

	private void createCharacter(int i) {
		Character character = new Character(name, "test", type, typeHpStats[i], typeDmgStats[i], typeSpdStats[i], typeRangeStats[i], new ItemVector().getVector(), null, false);
		party.party.add(character);
		start.characters.addElement(character);
		if(character.equipped != null){
			start.items.addElement(character.equipped);
		}
		for(Object itemObj : character.items){
			if(itemObj != null){
				start.items.add(itemObj);
			}
		}
		currentLocation = start;
		showLocation();
	}

	private boolean showInventory() {
		List<Character> characterList = new ArrayList<Character>();//list of characters
		for (Object characterObj : party.party) {//iterate through party (allied characters)
			if (characterObj instanceof Character) {
				characterList.add((Character) characterObj);//add said characters to list
			}
		}
		if (characterList.isEmpty()) {//if no party members
			displayOutput.append("\n Error : You have no items\n");//no items
			return false; //inventory open failed
		}

		displayOutput.append("\n");//new line before list of items

		List<Item> itemList = new ArrayList<Item>();//list of items
		for (Character c : characterList) {//iterate through characters
			if(c.equipped != null){//if null, don't add
				itemList.add(c.equipped);
			}
			for (Object itemObj : c.items) {//iterate through inventory of each characters
				if(itemObj != null){//if not null
					itemList.add((Item) itemObj);//add item
				}
			}

		}
		
		if(itemList.isEmpty()){//if the item list is empty
			displayOutput.append("\n Error : You have no items\n");//no items
			return false; //inventory open failed
		}
		
		for (Item i : itemList) {//iterate through items
			displayOutput.append(i.name + " : " + i.description + "\n");//display name of item and description
		}


		displayOutput.append("\n");

		return true; //inventory open successful
	}

	private boolean ask(String command) {
		boolean ret = false;
		if (charInit == 0) {//user is inputting name
			name = command;//no uppercase
			displayOutput.append("\nHello, " + name + "!\n");//respond to user
			commandInput.setText(new String());//need to clear input box because the rest of this loop will execute before the end of action() 
			charInit++;//next step
			ret = true;//success
		}
		if (charInit == 1) {//if choosing class
			if(uselessBoolean){//makes this block only run once
				displayOutput.append("\nWhat is your class?");//prompt user
				displayOutput.append("\n");
				for(String s : types){
					displayOutput.append(" -" + s + "\n");//displays choices for classes
				}
				uselessBoolean = false;
			}
			for (int i = 0; i < types.length; i++) {//iterate through classes
				if (command.equals(types[i].toUpperCase())) {//if the user input is valid
					type = command;//set the user type (in uppercase)
					char[] c = type.toCharArray();//char array
					if (c[0] == 'a' || c[0] == 'e' || c[0] == 'i' || c[0] == 'o' || c[0] == 'u') {//if first letter is a vowel
						displayOutput.append("\nAha! So you are an " + type.toLowerCase() + ", " + name + "!\n");//say "an"
					} else {
						displayOutput.append("\nAha! So you are a " + type.toLowerCase() + ", " + name + "!\n");//say "a"
					}
					commandInput.setText(new String());//reset string (just in case)
					charInit++;//next step
					createCharacter(i);//generate character
					ret = true;//success
				}
			}
		}
		return ret;
	}
	
}
