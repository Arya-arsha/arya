package com.Arya.Moviesfinder.controller;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JViewport;

public class CancelButton implements ActionListener {
	
	
	private Container container=null;
	private ArrayList<Component>allComponents=new ArrayList<Component>();
	
	
	public CancelButton(Container container) {
		this.container=container;
	
	}
	private  void getAllComponents(Container childContainer)
	{
		Component components[]=childContainer.getComponents();
		for(Component component:components)
		{
			if(component instanceof JPanel ||
					component instanceof JScrollPane||
					component instanceof JTabbedPane ||
					component instanceof JDialog ||
			        component instanceof JFrame ||
			        component instanceof JViewport)
				{
				getAllComponents((Container)component);
				
				}
			else 
				{
					allComponents.add(component);
					
				}
			}
			
	}
	@SuppressWarnings("rawtypes")
	public void resetData()
	{
		ArrayList<JComponent> swingComponents=new ArrayList<JComponent>();
		getAllComponents(container);
		for(Component component:allComponents)
		{
			if(component instanceof JComponent)
			{
				swingComponents.add((JComponent)component);
				
			}
		}
		for(JComponent swingComponent:swingComponents) {
			if(swingComponent instanceof JTextField){
				((JTextField)swingComponent).setText(null);
				
			}
			else if(swingComponent instanceof JComboBox)
			{
				((JComboBox)swingComponent).setSelectedItem(null);
			}
		
			else if(swingComponent instanceof JTextArea)
				
			{
				
			((JTextArea) swingComponent).setText(null);
								
					}
			else if(swingComponent instanceof JRadioButton)
					{
						((JRadioButton) swingComponent).getModel().getGroup().clearSelection();
					}
			else if(swingComponent instanceof JCheckBox) {

				((JCheckBox) swingComponent).setSelected(false);
				
			}
			else if (swingComponent instanceof JList) {
				
				((JList)swingComponent).clearSelection();
			} 
			
			
			}	
		
				
		}
		
	

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		resetData();
		

	}

}
