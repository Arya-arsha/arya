package com.Arya.Moviesfinder.controller;

import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JViewport;

import com.toedter.calendar.JTextFieldDateEditor;

public class Componentfinder {
	private Container  container=null;
	private ArrayList<Component>allComponents=new ArrayList<Component>();
	public Componentfinder(Container container) {
	super()	;
	this.container=container;
	}
	private void getAllComponents(Container childContainer) {
		Component components[]=childContainer.getComponents();
		for(Component component:components) {
			if(component instanceof JPanel ||
				component instanceof JScrollPane ||	
				component instanceof JTabbedPane ||
				component instanceof JDialog ||
				component instanceof JFrame ||
				component instanceof JViewport) {
				
				allComponents.add(component);
				getAllComponents((Container)component);
				
			}
			else {
				allComponents.add(component);
			}
					
		}
	}
	public Component findComponent(String name) {
		getAllComponents(container);
		for (Component item: allComponents)
		{
			if(!(item instanceof JViewport || 
					item instanceof JScrollBar ||
					item instanceof JTextFieldDateEditor||
					item instanceof JLayeredPane)){
					
		if(item.getName().equals(name)) {
			return item;
		}
			}
		}
		return null;
		}
}
			
		
	
