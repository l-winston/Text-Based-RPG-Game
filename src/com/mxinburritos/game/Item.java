package com.mxinburritos.game;

public class Item {
	private String m_title;
	private String m_description;
	private int m_damage;
	private int m_accuracy;
	private int m_value;

	public Item(String title, String description, int damage, int accuracy, int value) {
		m_title = title;
		m_description = description;
		m_damage = damage;
		m_accuracy = accuracy;
		m_value = value;
		
	}
	
	public int getDamage(){
		return m_damage;
	}
	
	public int getValue(){
		return m_value;
	}
	
	public int getAccuracy(){
		return m_accuracy;
	}
	
	public String getDescription(){
		return m_description;
	}
	
	public String getTitle(){
		return m_title;
	}
	
}
