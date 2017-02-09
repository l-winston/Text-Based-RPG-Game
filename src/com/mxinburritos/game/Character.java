package com.mxinburritos.game;

import java.util.Vector;

public class Character {
	public String name;
	public String description;
	public String type;
	public double maxHp;
	public double dmgMod;
	public double spd;
	public int range;
	public Item equipped;
	public boolean isEnemy;

	public double hp;

	public Character(String name, String description, String type, double maxHp, double dmgMod, double spd, int range, Item equipped, boolean isEnemy) {
		this.name = name;
		this.description = description;
		this.type = type;
		this.maxHp = maxHp;
		hp = maxHp;
		this.dmgMod = dmgMod;
		this.spd = spd;
		this.range = range;
		this.equipped = equipped;
		this.isEnemy = isEnemy;
	}

}
