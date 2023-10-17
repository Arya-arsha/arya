package com.Arya.Moviesfinder.controller;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.Arya.Moviesfinder.model.CURDSeries;
import com.Arya.Moviesfinder.model.SeriesModel;

public class SeriesUpdateController implements ActionListener {
	
	private Container container=null;
	private SeriesModel series=null;
	public SeriesUpdateController(Container container,SeriesModel series)
	{
		super();
		this.container =container;
		this.series=series;
	}
	@SuppressWarnings("unchecked")
	private void setData() {

		Componentfinder finder=new Componentfinder(container);
		series.setSeriesname(((JTextField) finder.findComponent("seriesname")).getText());
		series.setLanguage(((JTextField) finder.findComponent("language2")).getText());
		List<String> gerneList=new ArrayList<String>();
		for (String genre :((JList<String>)finder.findComponent("genre list")).getSelectedValuesList() ) {
			
			gerneList.add(genre);
		}
		series.setGenereList(gerneList);
		if(((JRadioButton)finder.findComponent("interest")).isSelected()) {
			series.setInterested(true);
		}
		else {
			series.setInterested(false);
		}
		if (series.isEmpty()) {
			throw new NullPointerException();
		}
	}
	public void actionPerformed(ActionEvent e) 
	{
		try {
			setData();
			new CURDSeries().updateSeries(series);
			new CancelButton(container).resetData();
			JOptionPane.showMessageDialog(null, "Series updated successfully"
					,"SUCCESSFUL", JOptionPane.PLAIN_MESSAGE);
		}  catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "Couldn't update series"
					,"WARNING", JOptionPane.WARNING_MESSAGE);
		} catch (NullPointerException e2) {
			// TODO: handle exception
			e2.printStackTrace();
			JOptionPane.showMessageDialog(null, "Series name  cannot be empty"
					,"WARNING", JOptionPane.WARNING_MESSAGE);
		} catch (Exception e2) {
			// TODO: handle exception
			e2.printStackTrace();
		}	
				
	}

}
