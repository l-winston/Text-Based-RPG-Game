package com.mxinburritos.game;

import java.util.Vector;

public class ItemVector {
	private Vector items;
	public ItemVector(){
		items = new Vector(0, 1);
		items.addElement(new Item("Key", "Its jagged edges suggest that it was made for a very unique lock", 0, 0, 0));
	} 
	public Vector getVector(){
		return items;
	}
	
}
