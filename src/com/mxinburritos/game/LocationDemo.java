package com.mxinburritos.game;

import java.applet.*;
import java.awt.*;
import java.util.*;

public class LocationDemo extends Applet
{
	Location currentLocation;
	String command;
	
	TextField commandInput;
	TextArea displayOutput;
	Button buttonInput;
	
	public LocationDemo()
	{
		super();
	}
	
	public void init()
	{
		super.init();
		setBackground(Color.black);
		setBackground(Color.white);
		
		Panel appletPanel = new Panel();
		
		BorderLayout b = new BorderLayout();
		appletPanel.setLayout(b);
		add(appletPanel);
		
		commandInput = new TextField(20);
		displayOutput = new TextArea(10,60);
		buttonInput = new Button("Go");
		Panel inputPanel = new Panel();
		
		inputPanel.add(commandInput);
		inputPanel.add(buttonInput);
		appletPanel.add("North", displayOutput);
		appletPanel.add("South", inputPanel);
		
		Location l1 = new Location("On your porch", "Grass surrounds you for miles and miles.\nThere is an outhouse right in front of you and your house behind you.\nIn front of you, there is a dirt and gravel road.");
		Location l2 = new Location("On the main road", "When you step out on to the road, you look\nboth ways. There is nothing\nseen better days. You see a small light switch behind the\ntoilet paper.");
		Location l3 = new Location("In a bright room", "You are suddenly you feel all the air in\nyou rush out like a balloon popping. After\nwhat seems like minutes, you are suddenly\nland in a room with lots of bright lights.\nIt takes a least 30 seconds for your eyes to adjust.");
		
		
		l1.addExit(new Exit(Exit.IN,l2));
		l2.addExit(new Exit(Exit.DOWN,l3));
		
		currentLocation = l1;
		
		showLocation();
		repaint();
	}
	
	private void showLocation()
	{
		displayOutput.appendText("\n" + currentLocation.getTitle() + "\n");
		displayOutput.appendText( "\n" );
		
		displayOutput.appendText( currentLocation.getDescription()+ "\n");
		
		displayOutput.appendText("\nAvailable exits: \n");
		for(Enumeration e = currentLocation.getExits().elements(); e.hasMoreElements();)
		{
			Exit an_exit = (Exit) e.nextElement();
			displayOutput.appendText(an_exit + "\n");
		}
	}
	
	public boolean action (Event evt, Object focus){
		String command;
		
		if(evt.target instanceof Button)
		{
			command = commandInput.getText();
			
			if(command.length()==0)
			{
				return true;
			}
			
			command = command.toUpperCase();
			
			for(Enumeration e = currentLocation.getExits().elements(); e.hasMoreElements();)
			{
				Exit an_exit = (Exit) e.nextElement();
				
				if( (an_exit.getDirectionName().compareTo(command)==0) || 
						(an_exit.getShortDirectionName().compareTo(command)==0))
				{
					currentLocation = an_exit.getLeadsTo();
					
					showLocation();
					
					commandInput.setText(t);
				}
			}
			
			
		}
	}
	
}
