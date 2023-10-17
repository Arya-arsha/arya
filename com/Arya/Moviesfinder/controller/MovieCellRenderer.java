package com.Arya.Moviesfinder.controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellRenderer;

@SuppressWarnings("serial")
public class MovieCellRenderer  extends JTextArea implements TableCellRenderer {


	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		if(column==1) {
			 setForeground(Color.BLUE);
		}
			
		else {
			setFont(new Font("Arial Black", Font.BOLD, 13));
			setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		
		}
		setWrapStyleWord(true);
		setLineWrap(true);
		setText(((String)value));
		
		
		return this;
	}

}
