package com.mxinburritos.game;

import java.util.Vector;

/**
 * @author winstonliu
 * this class is a Vector of all items in the game
 */
public class ItemVector {
	private Vector items;
	public ItemVector(){
		items = new Vector(0, 1);
	} 
	public Vector getVector(){
		return items;
	}
	
}
