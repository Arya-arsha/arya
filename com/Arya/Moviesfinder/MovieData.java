package com.Arya.Moviesfinder;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.Arya.Moviesfinder.controller.DataChangeController;
import com.Arya.Moviesfinder.controller.MovieCellRenderer;
import com.Arya.Moviesfinder.model.Model;
import com.Arya.Moviesfinder.model.MovieModel;
import com.Arya.Moviesfinder.model.SeriesModel;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;

import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Toolkit;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class MovieData extends JFrame {

	private JPanel contentPane;
	private JTable dataTable;
	private MovieModel movieModel=null;
	private SeriesModel seriesModel=null;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	

	/**
	 * Create the frame.
	 */
	public MovieData( final Model model) {
		setResizable(false);
		if(model instanceof MovieModel) {
			movieModel=(MovieModel)model;
		}
		else {
			seriesModel=(SeriesModel)model;
		}
		
		setTitle("MOVIE INFORMATION");
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
		
		setBounds(200, 0, 801, 696);
		contentPane = new JPanel();
		contentPane.setName("copnl");
		contentPane.setBackground(new Color(153, 204, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JPanel imagePanel = new JPanel();
		imagePanel.setBounds(15, 59, 165, 214);
		imagePanel.setName("panel1");
		imagePanel.setLayout(new BorderLayout(0, 0));
		try {
			if (model.getImage()!=null) {
				ImageIcon imageIcon=new ImageIcon(ImageIO.read(new BufferedInputStream(model.getImage()))
						.getScaledInstance(165, 214,Image.SCALE_SMOOTH));
				JLabel label = new JLabel(imageIcon);
				imagePanel.add(label);
				model.getImage().reset();
			} else {
				ImageIcon imageIcon=new ImageIcon(ImageIO.read(new BufferedInputStream(new 
						  FileInputStream("src/main/resources/no poster.jpeg")))
						.getScaledInstance(165, 214,Image.SCALE_SMOOTH));
				JLabel label = new JLabel(imageIcon);
				imagePanel.add(label);

			}
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		imagePanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		
		
		JLabel label1 = new JLabel(" STATUS :");
		label1.setBounds(356, 89, 117, 42);
		label1.setName("lblst");
		label1.setForeground(new Color(0, 0, 153));
		label1.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		
		JButton cancelButton = new JButton("CANCEL");
		cancelButton.setFocusPainted(false);
		cancelButton.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		cancelButton.setBackground(new Color(0, 139, 139));
		cancelButton.setBounds(341, 600, 98, 48);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
		cancelButton.setName("cnbtn");
		cancelButton.setForeground(new Color(0, 0, 0));
	
		JButton editButton = new JButton("EDIT");
		editButton.setBackground(new Color(0, 139, 139));
		editButton.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		editButton.setFocusPainted(false);
		editButton.setBounds(490, 600, 98, 48);
		editButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
		editButton.setName("edbtn");
		editButton.setForeground(new Color(0, 0, 0));
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MovieInsertTab frame = new MovieInsertTab(model);
					frame.setVisible(true);
				} catch (Exception f) {
					f.printStackTrace();
				}
			}
		});
		
		JLabel headLabel = new JLabel("MOVIE INFO");
		headLabel.setBounds(5, 5, 781, 48);
		headLabel.setHorizontalAlignment(SwingConstants.CENTER);
		headLabel.setOpaque(true);
		headLabel.setName("lblmo");
		headLabel.setBackground(new Color(32, 178, 170));
		headLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		
		JRadioButton watchRdbtn = new JRadioButton("WATCHED");
		watchRdbtn.setFocusPainted(false);
		watchRdbtn.setBounds(356, 145, 147, 34);
		watchRdbtn.setForeground(new Color(0, 0, 128));
		watchRdbtn.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		watchRdbtn.setOpaque(false);
		
		buttonGroup.add(watchRdbtn);
		
		JRadioButton notwatchRdbtn = new JRadioButton("NOT WATCHED");
		notwatchRdbtn.setFocusPainted(false);
		notwatchRdbtn.setBounds(505, 142, 223, 41);
		notwatchRdbtn.setForeground(new Color(0, 0, 128));
		notwatchRdbtn.setOpaque(false);
		notwatchRdbtn.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		buttonGroup.add(notwatchRdbtn);
		
		JToggleButton favouriteButton = new JToggleButton("NOT FAVOURITE",model.isFavourite());
		favouriteButton.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		favouriteButton. setContentAreaFilled(false);
		favouriteButton.setOpaque(true);
		favouriteButton.setBounds(356, 199, 208, 34);
		favouriteButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		favouriteButton.setForeground(new Color(0, 0, 0));
		favouriteButton.setFocusPainted(false);
		
		if (model.isFavourite()) {  
			
			favouriteButton.setText("FAVOURITE");
			favouriteButton.setBackground(new Color(255, 69, 0));
		}
		favouriteButton.addItemListener(new DataChangeController(favouriteButton,model.getId()));
		
		JLabel watchLabel = new JLabel("");
		watchLabel.setBounds(479, 89, 189, 42);
		watchLabel.setName("watch lbl");
		watchLabel.setForeground(new Color(0, 0, 153));
		watchLabel.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		watchRdbtn.addItemListener(new DataChangeController(watchRdbtn,watchLabel,model.getId()));
		notwatchRdbtn.addItemListener(new DataChangeController(notwatchRdbtn,watchLabel,model.getId()));
		
		JTextArea movieNametextArea = new JTextArea();
		
		movieNametextArea.setFont(new Font("Monospaced", Font.BOLD, 20));
		movieNametextArea.setOpaque(false);
		movieNametextArea.setLineWrap(true);
		movieNametextArea.setEditable(false);
		movieNametextArea.setBounds(15, 279, 205, 82);
		if(model instanceof MovieModel) {
			movieNametextArea.setText(movieModel.getMovieName());
			}
		else {
		    movieNametextArea.setText(seriesModel.getSeriesname());
			}
		if(model.isInterested()) {
			movieNametextArea.setForeground(new Color(0, 128, 0));
		}
		else {
			movieNametextArea.setForeground(Color.RED);
		}
		contentPane.setLayout(null);
		contentPane.add(cancelButton);
		contentPane.add(editButton);
		contentPane.add(headLabel);
		contentPane.add(imagePanel);
		contentPane.add(movieNametextArea);
		contentPane.add(watchRdbtn);
		contentPane.add(notwatchRdbtn);
		contentPane.add(favouriteButton);
		contentPane.add(label1);
		contentPane.add(watchLabel);
		if(model.isWatched()) {
			watchLabel.setText("WATCHED");
			watchRdbtn.setSelected(true);
		
		}
		else {
			watchLabel.setText("NOT WATCHED");
			notwatchRdbtn.setSelected(true);
		}
		
		JScrollPane dataScrollPane = new JScrollPane();
		dataScrollPane.setBorder(new LineBorder(new Color(130, 135, 144), 1, true));
		dataScrollPane.setBackground(new Color(255, 255, 255));
		dataScrollPane.setBounds(230, 248, 437, 341);
		contentPane.add(dataScrollPane);
		dataTable = new JTable();
		dataTable.setShowGrid(false);
		dataTable.setTableHeader(null);
		DefaultTableModel tableModel=new DefaultTableModel(0,2);
		dataTable.setModel(tableModel);
		dataTable.getColumnModel().getColumn(0).setCellRenderer(new MovieCellRenderer());
		dataTable.getColumnModel().getColumn(1).setCellRenderer(new MovieCellRenderer());
		if(movieModel!=null) {
			tableModel.addRow( new String[] {":MOVIE NAME ",movieModel.getMovieName()});
			tableModel.addRow( new String[] {":THEATRE/OTT ",movieModel.getReleaseType()});
			tableModel.addRow( new String[] {":THEATRE RELEASE DATE ",
					movieModel.getThreatreReleaseDate()!=null?movieModel.getThreatreReleaseDate().toString():"Unknown"});
			tableModel.addRow( new String[] {":OTT RELEASE DATE ",
					movieModel.getOttReleaseDate()!=null?movieModel.getOttReleaseDate().toString():"Unknown"});
		}
		else {
			tableModel.addRow( new String[] {":SERIES NAME ",seriesModel.getSeriesname()});
			tableModel.addRow( new String[] {":SEASON",seriesModel.getSeason().getSeason()+""});
			tableModel.addRow( new String[] {":EPISODES",seriesModel.getSeason().getEpisodeNumber()+""});
			tableModel.addRow( new String[] {":OTT RELEASE DATE ",
					seriesModel.getSeason().getReleaseDate()!=null?seriesModel
							.getSeason().getReleaseDate().toString():"Unknown"});
			
		}
		tableModel.addRow( new String[] {":LAGUAGAGE ",model.getLanguage()});
		tableModel.addRow( new String[] {":GENRE ",model.listString()});
		tableModel.addRow( new String[] {":INTERESTED ",model.isInterested()?"Interseted":"Not Interested"});
		dataTable.setRowHeight(dataScrollPane.getHeight()/dataTable.getRowCount());
		dataScrollPane.setViewportView(dataTable);
		dataTable.setName("tb1");
	}
}
