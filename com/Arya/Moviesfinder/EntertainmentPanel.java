package com.Arya.Moviesfinder;

import javax.swing.JPanel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import java.awt.ComponentOrientation;
import java.awt.Dimension;

import com.Arya.Moviesfinder.controller.CheckController;
import com.Arya.Moviesfinder.model.CURDMovie;
import com.Arya.Moviesfinder.model.CURDSeries;
import com.Arya.Moviesfinder.model.Model;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class EntertainmentPanel extends JPanel {
	private JComboBox<String> genreCombox=null;
	public static String  text=null;
	

	/**
	 * Create the panel.
	 */
	public EntertainmentPanel() {
		setForeground(new Color(0, 0, 0));
		setName("main panel");
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setBackground(new Color(153, 204, 204));                                          
     	setBounds(0,0,944,876);
		
		JLabel title = new JLabel("ENTERTAINMENT");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setForeground(Color.RED);
		title.setBackground(new Color(32, 178, 170));
		title.setOpaque(true);
		title.setName("title");
		title.setHorizontalTextPosition(SwingConstants.CENTER);
		title.setFont(new Font("Times New Roman", Font.BOLD, 36));
		
		JLabel nameLabel = new JLabel("MOVIE NAME :");
		nameLabel.setName("name label");
		nameLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		nameLabel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		nameLabel.setForeground(Color.BLUE);
		nameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
	    genreCombox = new JComboBox<String>();
	    genreCombox.setName("genre");
	    DefaultComboBoxModel<String> genreList=new DefaultComboBoxModel<String>();
	    try {
			genreList.addAll(new CURDMovie().retriveGenre(null));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    genreCombox.setModel(genreList);
	    genreCombox.setSelectedIndex(0);
	    
		
		JLabel genreLabel = new JLabel("GENRE :");
		genreLabel.setName("genrelbl");
		genreLabel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		genreLabel.setForeground(Color.BLUE);
		genreLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		JComboBox<String> movieNameCombox = new JComboBox<String>();
		movieNameCombox.setName("movie name");
		movieNameCombox.setEditable(true);
		DefaultComboBoxModel<String> comboModel=new DefaultComboBoxModel<String>();
		movieNameCombox.setModel(comboModel);
		movieNameCombox.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
			
			
				 text=(String)movieNameCombox.getEditor().getItem();
				 comboModel.removeAllElements();
				 movieNameCombox.getEditor().setItem(text);
				try {
					comboModel.addAll(new CURDMovie().retriveMovieName(text));
					comboModel.addAll(new CURDSeries().retriveSeriesName(text));
					movieNameCombox.showPopup();
					movieNameCombox.getEditor().setItem(text);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		JLabel dateLabel = new JLabel("RELEASE DATE");
		dateLabel.setName("datelbl");
		dateLabel.setForeground(Color.BLUE);
		dateLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		JButton checkButton = new JButton("CHECK MOVIE");
		checkButton.setFocusPainted(false);
		checkButton.setBackground(new Color(0, 139, 139));
		checkButton.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		checkButton.setName("check");
		checkButton.setForeground(new Color(0, 0, 0));
		checkButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		checkButton.addActionListener(new CheckController(this));
		JButton addButton = new JButton("ADD NEW MOVIE");
		addButton.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		addButton.setBackground(new Color(0, 139, 139));
		addButton.setFocusPainted(false);
		addButton.setName("add");
		addButton.setForeground(new Color(0, 0, 0));
		addButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MovieInsertTab frame = new MovieInsertTab(null);
					frame.setVisible(true);
				} catch (Exception f) {
					f.printStackTrace();
					JOptionPane.showMessageDialog(null, "Couldn't load"
							,"WARNING", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		JDateChooser releaseDate = new JDateChooser();
		releaseDate.getCalendarButton().setName("datecalender");
		releaseDate.setName("date");
		((JTextFieldDateEditor)releaseDate.getDateEditor()).setEditable(false);
		CURDMovie curdMovie=null;
		CURDSeries curdSeries=null;
		try {
			curdMovie = new CURDMovie();
			curdSeries=new CURDSeries();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ImagePanel actionPanel =null;
		
		JScrollPane ScrollFavu = new JScrollPane();
		ScrollFavu.setBorder(null);
		ScrollFavu.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		ScrollFavu.setAutoscrolls(true);
		ScrollFavu.getHorizontalScrollBar().setUnitIncrement(25);
		ScrollFavu.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		ScrollFavu.getHorizontalScrollBar().setPreferredSize(new Dimension(0,0));
		ScrollFavu.setOpaque(false);
		ScrollFavu.setName("favou scroll");
		
		JPanel panelFavourite = new JPanel();
		panelFavourite.setBorder(new TitledBorder(new LineBorder(new Color(0, 255, 0), 2, true), "FAVOURITE  ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		panelFavourite.setBackground(new Color(0, 0, 0));
		panelFavourite.setAutoscrolls(true);
		FlowLayout flowLayout = (FlowLayout) panelFavourite.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		flowLayout.setAlignment(FlowLayout.LEADING);
		panelFavourite.setName("favourite");
		ScrollFavu.setViewportView(panelFavourite);
		try {
			for (Model model :curdMovie.retriveMovies("favorite",4) ) {
				actionPanel=new ImagePanel(model);
				panelFavourite.add(actionPanel);		
			}
			for (Model model : curdSeries.retriveSeries("favourite", 4)) {
				actionPanel=new ImagePanel(model);
				panelFavourite.add(actionPanel);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JScrollPane scrollInterested = new JScrollPane();
		scrollInterested.setBorder(null);
		scrollInterested.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollInterested.getHorizontalScrollBar().setPreferredSize(new Dimension(0,0));
		scrollInterested.setAutoscrolls(true);
		scrollInterested.getHorizontalScrollBar().setUnitIncrement(25);
		scrollInterested.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollInterested.setOpaque(false);
		scrollInterested.setName("scroll interested");
		
		JPanel interest = new JPanel();
		interest.setBorder(new TitledBorder(new LineBorder(new Color(21, 234, 43), 2, true), "INTERESTED  ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(248, 248, 255)));
		interest.setBackground(new Color(0, 0, 0));
		interest.setAutoscrolls(true);
		FlowLayout flowLayout_1 = (FlowLayout) interest.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEADING);
		flowLayout_1.setVgap(0);
		flowLayout_1.setHgap(0);
		
		interest.setName("interested panel");
		try {
			for (Model model :curdMovie.retriveMovies("interested",4) ) {
				actionPanel=new ImagePanel(model);
				interest.add(actionPanel);		
			}
			for (Model model :curdSeries.retriveSeries("interested",4) ) {
				actionPanel=new ImagePanel(model);
				interest.add(actionPanel);		
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		scrollInterested.setViewportView(interest);
		
		JScrollPane scrollPaneUpcoming = new JScrollPane();
		scrollPaneUpcoming.setBorder(null);
		scrollPaneUpcoming.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPaneUpcoming.setAutoscrolls(true);
		scrollPaneUpcoming.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPaneUpcoming.getHorizontalScrollBar().setPreferredSize(new Dimension(0,0));
		scrollPaneUpcoming.getHorizontalScrollBar().setUnitIncrement(25);
		scrollPaneUpcoming.setOpaque(false);
		scrollPaneUpcoming.setName("upcoming scroll");
		
		JPanel upComing = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) upComing.getLayout();
		flowLayout_2.setVgap(0);
		flowLayout_2.setHgap(0);
		flowLayout_2.setAlignment(FlowLayout.LEADING);
		upComing.setBorder(new TitledBorder(new LineBorder(new Color(21, 234, 43), 2, true),
				"UPCOMING  ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		upComing.setBackground(new Color(0, 0, 0));
		upComing.setAutoscrolls(true);
		upComing.setName("upcoming panel");
		try {
			for (Model model :curdMovie.retriveMovies(null,5) ) {
				actionPanel=new ImagePanel(model);
				upComing.add(actionPanel);
				}
			for (Model model :curdMovie.retriveMovies(null,6) ) {
				actionPanel=new ImagePanel(model);
				upComing.add(actionPanel);
				}
			for (Model model :curdSeries.retriveSeries(null,5) ) {
				actionPanel=new ImagePanel(model);
				upComing.add(actionPanel);
				}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		scrollPaneUpcoming.setViewportView(upComing);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(title, GroupLayout.DEFAULT_SIZE, 995, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(42)
					.addComponent(nameLabel, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
					.addGap(43)
					.addComponent(movieNameCombox, 0, 291, Short.MAX_VALUE)
					.addGap(118)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(dateLabel, GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(releaseDate, GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
							.addGap(99)))
					.addGap(49))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(40)
					.addComponent(genreLabel, GroupLayout.PREFERRED_SIZE, 134, Short.MAX_VALUE)
					.addGap(43)
					.addComponent(genreCombox, GroupLayout.PREFERRED_SIZE, 228, Short.MAX_VALUE)
					.addGap(548))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(269)
					.addComponent(checkButton, GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
					.addGap(157)
					.addComponent(addButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(169))
				.addComponent(scrollPaneUpcoming, GroupLayout.DEFAULT_SIZE, 995, Short.MAX_VALUE)
				.addComponent(ScrollFavu, GroupLayout.DEFAULT_SIZE, 995, Short.MAX_VALUE)
				.addComponent(scrollInterested, GroupLayout.DEFAULT_SIZE, 995, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(title, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
					.addGap(38)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(nameLabel, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addComponent(movieNameCombox, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(dateLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
							.addGap(11)
							.addComponent(releaseDate, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)))
					.addGap(36)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(genreLabel, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
								.addComponent(genreCombox, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
							.addGap(53)
							.addComponent(checkButton, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
						.addComponent(addButton, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(scrollPaneUpcoming, GroupLayout.PREFERRED_SIZE, 300, Short.MAX_VALUE)
					.addGap(0)
					.addComponent(ScrollFavu, GroupLayout.PREFERRED_SIZE, 300, Short.MAX_VALUE)
					.addGap(0)
					.addComponent(scrollInterested, GroupLayout.PREFERRED_SIZE, 300, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
}
