package com.mxinburritos.game;

import java.util.*;

public class Location {
	private String m_roomTitle;
	private String m_roomDescription;
	private Vector m_vecExits;
	
	public Location( String title, String description)
	{
		m_roomTitle = title;
		m_roomDescription = description;
		m_vecExits = new Vector();
	}
	
	public String toString()
	{
		return m_roomTitle;
	}
	
	public String getTitle()
	{
		return m_roomTitle;
	}
	
	public void setTitle(String roomTitle)
	{
		m_roomTitle = roomTitle;
	}
	
	public String getDescription()
	{
		return m_roomDescription;
	}
	
	public void setDescription(String roomDescription)
	{
		m_roomDescription = roomDescription;
	}
	
	public void addExit(Exit exit)
	{
		m_vecExits.addElement(exit);
	}
	
	public void removeExit(Exit exit)
	{
		if(m_vecExits.contains(exit))
		{
			m_vecExits.remove(exit);
		}
	}
	
	public Vector getExits()
	{
		return (Vector) m_vecExits.clone();
	}
}
