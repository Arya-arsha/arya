package com.Arya.Moviesfinder.controller;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.Arya.Moviesfinder.MovieList;
import com.Arya.Moviesfinder.model.CURDMovie;
import com.Arya.Moviesfinder.model.CURDSeries;
import com.Arya.Moviesfinder.model.Model;
import com.Arya.Moviesfinder.model.SeriesModel;
import com.toedter.calendar.JDateChooser;

public class CheckController implements ActionListener {
	private Componentfinder finder=null;
	private String name=null;
	private String genre=null;
	private Date date=null;
	private List<Model> models=new ArrayList<Model>();
	private List<SeriesModel> seriesModels= new ArrayList<SeriesModel>();
	public CheckController(Container container) {
	finder=new Componentfinder(container);
   }
	private void findMovies() throws SQLException {
		// to find all the movies
		// select all row for the query then remove duplicate by movie id
		if(name!=null) {
		    removeDuplicate(new CURDMovie().retriveMovies(name, 1));
			removeSeriesDuplicate(new CURDSeries().retriveSeries(name, 1));
		}
		if (genre!=null) {
			removeDuplicate(new CURDMovie().retriveMovies(genre, 3));
			removeSeriesDuplicate(new CURDSeries().retriveSeries(genre, 3));
		}
		if(date!=null) {
			removeDuplicate(new CURDMovie().retriveMovies(date, 2));
			removeSeriesDuplicate(new CURDSeries().retriveSeries(date, 2));
		}
	}
	private void removeDuplicate(List<Model> tempModels) {
		//to remove duplicated elements in list try use contains
		for (Model model : tempModels) {
			if(!(models.contains(model))) {
				models.add(model);
			}	
		}

	}
	private void removeSeriesDuplicate(List<Model> tempModels) {
		//to remove duplicated elements in list try use contains
		for (Model model : tempModels) {
			SeriesModel seriesModel=(SeriesModel)model;
			if(!(seriesModels.contains(seriesModel))) {
				seriesModels.add(seriesModel);
			}	
		}

	}

	@SuppressWarnings("unchecked")
	public void actionPerformed(ActionEvent e) {
		name=(String) ((JComboBox<String>)finder.findComponent("movie name")).getSelectedItem();
		genre=(String) ((JComboBox<String>)finder.findComponent("genre")).getSelectedItem();
		java.util.Date utilDate= ((JDateChooser)finder.findComponent("date")).getDate();
		
		if(utilDate!=null) {
			date=new Date(utilDate.getTime());
			}
		try {
			findMovies();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if((name!=null || date!=null || !(genre.equals("Select a genre")))&&!(models.isEmpty())){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
			
					models.addAll(seriesModels);
					MovieList frame = new MovieList(models);
					models.clear();
					frame.setVisible(true);
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Couldn't process the request\n try again"
							,"LOADING ERROR", JOptionPane.WARNING_MESSAGE);
				}
			}
		});

	}
	if(models.isEmpty()) {
		
		if(name==null && date==null && genre.equals("Select a genre")) {
			JOptionPane.showMessageDialog(null, "Please enter a criteria for searching"
					,"INFORMATION", JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			JOptionPane.showMessageDialog(null, "No movies finded for this query...."
					,"INFORMATION", JOptionPane.INFORMATION_MESSAGE);
		}
			
	}
	
	}


	

}
