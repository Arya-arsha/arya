package com.Arya.Moviesfinder.controller;

import java.awt.event.KeyEvent;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.sql.SQLException;

import javax.swing.JComboBox;


public class MovieNameFinder implements TextListener{
	@SuppressWarnings("unused")
	private JComboBox<String> nameBox=null;
	

	public MovieNameFinder(JComboBox<String> nameBox) {
		super();
		this.nameBox = nameBox;
	}

	public void setNames() throws SQLException {
		/*
		 * ((DefaultComboBoxModel<String>)nameBox.getModel()) .addAll(new
		 * Addmovie().retriveMovies((String)nameBox.getSelectedItem()));
		 * System.out.println("kkk"+(String)nameBox.getEditor().getItem());
		 */
		
	}

	//@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		try {
			setNames();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void textValueChanged(TextEvent e) {
		// TODO Auto-generated method stub
		
	}
}
