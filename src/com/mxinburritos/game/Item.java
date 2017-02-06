package com.mxinburritos.game;

public class Item {
	public String name;
	public String description;
	public int damage;
	public double accuracy;
	public int range;
	public int splashRange;
	public boolean isWeapon;

	/**
	 * @param title 		name of the item
	 * @param description 	description of the item
	 * @param damage 		damage the item does
	 * @param accuracy 		how accurate the item is
	 * @param range 		how far the item can hit (int)
	 * @param splashRange 	how far the splash of the item hits (int)
	 * @param isWeapon 		whether the item is a weapon (true) or not (false)
	 */
	public Item(String name, String description, int damage, int accuracy, int range, int splashRange, boolean isWeapon) {
		this.name = name;
		this.description = description;
		this.damage = damage;
		this.accuracy = accuracy;
		this.range = range;
		this.splashRange = splashRange;
		this.isWeapon = isWeapon;

	}

}
