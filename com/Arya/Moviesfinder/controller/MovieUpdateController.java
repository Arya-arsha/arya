package com.Arya.Moviesfinder.controller;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.Arya.Moviesfinder.model.CURDMovie;
import com.Arya.Moviesfinder.model.Model;
import com.Arya.Moviesfinder.model.MovieModel;
import com.toedter.calendar.JDateChooser;

public class MovieUpdateController implements ActionListener {
	private Componentfinder finder=null;
	private Container container =null;
	private MovieModel movie=null;
	public MovieUpdateController(Container container,Model model) {
	super();
	this.container=container;
	finder=new Componentfinder(container);
	this.movie=(MovieModel)model;
	}
	@SuppressWarnings({ "unchecked" })
	private void getData() throws FileNotFoundException {
		
		
		movie.setMovieName(((JTextField) finder.findComponent("moviename")).getText());
		movie.setLanguage(((JTextField) finder.findComponent("language")).getText());
		if(((JRadioButton)finder.findComponent("rdbn1")).isSelected()) {
			movie.setReleaseType("Theatre");
		}
		else {
			movie.setReleaseType("Ott");
		}
		if(((JRadioButton)finder.findComponent("interest")).isSelected()) {
			movie.setInterested(true);
		}
		else {
			movie.setInterested(false);
		}
       java.util.Date  ottDate= ((JDateChooser)finder.findComponent("JDateChooserott")).getDate();
		
		if(ottDate!=null) {
			movie.setOttReleaseDate(new Date(ottDate.getTime()));
		}
       java.util.Date  theatreDate= ((JDateChooser)finder.findComponent("JDateChoosertheatre")).getDate();
		
		if(theatreDate!=null) {
			movie.setThreatreReleaseDate(new Date(theatreDate.getTime()));
		}
		List<String> gerneList=new ArrayList<String>();
		for (String genre :((JList<String>)finder.findComponent("generemovie")).getSelectedValuesList() ) {
			
			gerneList.add(genre);
		}
		movie.setGenereList(gerneList);
		if(Model.IMAGE!=null) {
		movie.setImage(new FileInputStream(Model.IMAGE)); 
		}
		if (movie.isEmpty()) {
			throw new NullPointerException();		
		}	
	}
	public void actionPerformed(ActionEvent e) {
			try {
				getData();
				new CURDMovie().updateMovie(movie);
				new CancelButton(container).resetData();
				JOptionPane.showMessageDialog(null, "Movie updated successfully"
						,"SUCCESSFUL", JOptionPane.PLAIN_MESSAGE);
			}  catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "Couldn't find file"
						,"WARNING", JOptionPane.WARNING_MESSAGE);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "Couldn't update movie"
						,"WARNING", JOptionPane.WARNING_MESSAGE);
			} catch (NullPointerException e2) {
				// TODO: handle exception
				e2.printStackTrace();
				JOptionPane.showMessageDialog(null, "Movie name cannot be empty"
						,"WARNING", JOptionPane.WARNING_MESSAGE);
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
			
	}

}
