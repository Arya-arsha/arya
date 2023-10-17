package com.Arya.Moviesfinder;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.Arya.Moviesfinder.model.Model;
import com.Arya.Moviesfinder.model.MovieModel;
import com.Arya.Moviesfinder.model.SeriesModel;

import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;

@SuppressWarnings("serial")
public class MovieInsertTab extends JFrame {

	private JPanel contentPane;

	
	/**
	 * Create the frame.
	 */
	public MovieInsertTab(Model model) {
		setBounds(0, 0, 1197, 730);
		Image icon=null;
		try {
			URL url = getClass().getResource("/resources/movieicon.jpg");
			icon=ImageIO
					.read(url)
					.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		} catch (IOException e) {
			e.printStackTrace();
		}
		setIconImage(icon);
		setTitle("MOVIES");
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		UIManager.put("TabbedPane.selected", new Color(11, 122, 244));
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 1207, Short.MAX_VALUE)
					.addGap(0))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 711, Short.MAX_VALUE)
					.addGap(0))
		);
		
		MoviePanel film=new MoviePanel(model instanceof MovieModel?(MovieModel)model:null);
		JLabel movieTab=new JLabel("MOVIE");
		Dimension size=new Dimension(549, 25);
		movieTab.setPreferredSize(size);
		movieTab.setHorizontalAlignment(SwingConstants.CENTER);
		tabbedPane.addTab( "movie", film);
		tabbedPane.setTabComponentAt(0, movieTab);
	
		SeriesPanel seri=new SeriesPanel(model instanceof SeriesModel?(SeriesModel)model:null);
		tabbedPane.addTab("Series",seri);
		JLabel seriesTab=new JLabel("SERIES");
		seriesTab.setPreferredSize(size);
		seriesTab.setHorizontalAlignment(SwingConstants.CENTER);
		tabbedPane.setTabComponentAt(1, seriesTab);
		if (model instanceof SeriesModel) {
			tabbedPane.setSelectedIndex(1);	
		} 
		contentPane.setLayout(gl_contentPane);
	}
}
