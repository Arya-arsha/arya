package com.Arya.Moviesfinder.controller;

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;

import com.Arya.Moviesfinder.model.CURDMovie;

public class DataChangeController implements ItemListener {

	private JToggleButton button=null;
	private JLabel label=null;
	private String id=null;
	
	
	public DataChangeController(JToggleButton button, JLabel label,String id) {
		super();
		this.button = button;
		this.label = label;
		this.id=id;
	}
	

	public DataChangeController(JToggleButton button,String id) {
		super();
		this.button = button;
		this.id=id;
	}

	private void favouriteChanged() throws SQLException {
		if(button.isSelected()) {
			button.setBackground(new Color(255, 69, 0));
			button.setText("FAVOURITE");
			
		}
		else {
			
			button.setBackground(Color.WHITE);
			button.setText("NOT FAVOURITE");
		}
	new CURDMovie().updateChoice(1, button.isSelected(),id );
		
	}
	private void watchChanged() throws SQLException {
		String watch=null;
		boolean choice;
		if(((JRadioButton)button).isSelected()) {
			label.setText(((JRadioButton)button).getActionCommand());
			watch=((JRadioButton)button).getActionCommand();
			
		}
		else {
		}
		if (watch!=null&& watch.equals("WATCHED")) {
			
			choice=true;
		}
		else {
			choice=false;
		}
		new CURDMovie().updateChoice(2,choice,id );
		
	}

	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub

		
			try {
				if(e.getItemSelectable() instanceof JRadioButton) {
				    watchChanged();
				}
			
			    else {
				    favouriteChanged();
			    }
			} 
			catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "Couldn't change"
						,"WARNING", JOptionPane.WARNING_MESSAGE);
			}
		
	}

}
