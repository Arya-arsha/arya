package com.Arya.Moviesfinder.controller;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.Arya.Moviesfinder.model.CURDSeries;
import com.Arya.Moviesfinder.model.Model;
import com.Arya.Moviesfinder.model.SeasonModel;
import com.Arya.Moviesfinder.model.SeriesModel;
import com.toedter.calendar.JDateChooser;

public class SeasonUpdateController implements ActionListener {

	private Container container=null;
	private SeasonModel season=null;
	private SeriesModel series=null;
	private JDialog dialog=null;
	public SeasonUpdateController(Container container,SeriesModel series,JDialog dialog) {
		this.container=container;
		this.series=series;
		this.dialog=dialog;
	}
	private void setData() throws FileNotFoundException {
		Componentfinder finder=new Componentfinder(container);
		season=series.getSeason();
		season.setSeason(Integer.parseInt(((JTextField) finder.findComponent("season")).getText()));
		season.setEpisodeNumber(Integer.parseInt(((JTextField) finder.findComponent("episode")).getText()));
		java.util.Date  ottDate= ((JDateChooser)finder.findComponent("release date")).getDate();
	    if(ottDate!=null) {
			season.setReleaseDate(new Date(ottDate.getTime()));
		}
	    if(Model.IMAGE!=null) {
			season.setSeasonImage(new FileInputStream(Model.IMAGE)); 
		}
	    if (season.isEmpty()) {
			throw new NullPointerException();
		}
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		try {
			setData();
			new CURDSeries().updateSeason(season, series.getSeriesId());
			dialog.dispose();
			JOptionPane.showMessageDialog(null, "Season updated successfully"
					,"SUCCESSFUL", JOptionPane.PLAIN_MESSAGE);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "Couldn't find file"
					,"WARNING", JOptionPane.WARNING_MESSAGE);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "Couldn't update season"
					,"WARNING", JOptionPane.WARNING_MESSAGE);
		} catch (NullPointerException e2) {
			// TODO: handle exception
			e2.printStackTrace();
			JOptionPane.showMessageDialog(null, "Season  cannot be 0 or negative and should be number "
					,"WARNING", JOptionPane.WARNING_MESSAGE);
		} catch (Exception e2) {
			// TODO: handle exception
			e2.printStackTrace();
		}
	}

}
