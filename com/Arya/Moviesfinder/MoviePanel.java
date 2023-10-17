package com.Arya.Moviesfinder;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.Arya.Moviesfinder.controller.Browsecontroller;
import com.Arya.Moviesfinder.controller.CancelButton;
import com.Arya.Moviesfinder.controller.MovieAddController;
import com.Arya.Moviesfinder.controller.MovieUpdateController;
import com.Arya.Moviesfinder.model.CURDMovie;
import com.Arya.Moviesfinder.model.MovieModel;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import java.awt.Color;
import java.sql.SQLException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingConstants;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class MoviePanel extends JPanel {

	
	private JTextField movieNameText;
	private JTextField languageText;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();

	
	
	/**
	 * Create the frame.
	 */

	public MoviePanel(MovieModel model) {
		setName("jpanelm");
		setBackground(new Color(153, 204, 204));
		setBounds(100, 100, 1030, 700);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JLabel movieLabel = new JLabel("Movie Name");
		movieLabel.setName("lbl1");
		movieLabel.setBackground(new Color(64, 0, 64));
		movieLabel.setForeground(new Color(102, 0, 153));
		movieLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		JLabel languageLabel = new JLabel("Language");
		languageLabel.setName("lbl2");
		languageLabel.setForeground(new Color(102, 0, 153));
		languageLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		movieNameText = new JTextField();
		movieNameText.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		movieNameText.setName("moviename");
		movieNameText.setColumns(10);
		
		languageText = new JTextField();
		languageText.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		languageText.setName("language");
		languageText.setColumns(10);
		
		JRadioButton theatreRdbn = new JRadioButton(" Theatre");
		theatreRdbn.setFocusPainted(false);
		theatreRdbn.setBackground(new Color(153, 204, 204));
		theatreRdbn.setName("rdbn1");
		theatreRdbn.setForeground(new Color(102, 0, 153));
		buttonGroup.add(theatreRdbn);
		theatreRdbn.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		JRadioButton ottRdbn = new JRadioButton("OTT");
		ottRdbn.setFocusPainted(false);
		ottRdbn.setBackground(new Color(153, 204, 204));
		ottRdbn.setName("rdbn2");
		ottRdbn.setForeground(new Color(102, 0, 153));
		buttonGroup.add(ottRdbn);
		ottRdbn.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		JLabel ottLabel = new JLabel("OTT Release Date");
		ottLabel.setName("lbl3");
		ottLabel.setForeground(new Color(102, 0, 153));
		ottLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		JLabel theatreLabel = new JLabel("Theatre Release Date");
		theatreLabel.setName("lbl4");
		theatreLabel.setForeground(new Color(102, 0, 153));
		theatreLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		JLabel imageLabel = new JLabel("Insert Image");
		imageLabel.setName("lbl5");
		imageLabel.setForeground(new Color(102, 0, 153));
		imageLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		JButton imageButton = new JButton("IMAGE");
		imageButton.setFocusPainted(false);
		imageButton.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		imageButton.setBackground(new Color(32, 178, 170));
		imageButton.setName("imgbtn");
		imageButton.addActionListener(new Browsecontroller());
		imageButton.setForeground(new Color(0, 0, 0));
		imageButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		JButton addButton = new JButton("ADD");
		addButton.setFocusPainted(false);
		addButton.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		addButton.setBackground(new Color(32, 178, 170));
		addButton.addActionListener(new MovieAddController(this));
		addButton.setName("addbtn");
		addButton.setForeground(new Color(0, 0, 0));
		addButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		JLabel genereLabel = new JLabel("Genre");
		genereLabel.setName("lbl6");
		genereLabel.setForeground(new Color(102, 0, 153));
		genereLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		JLabel HeadLabel = new JLabel("MOVIE DETAILS");
		HeadLabel.setHorizontalAlignment(SwingConstants.CENTER);
		HeadLabel.setName("lbl7");
		HeadLabel.setOpaque(true);
		HeadLabel.setForeground(new Color(0, 0, 0));
		HeadLabel.setBackground(new Color(32, 178, 170));
		HeadLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
		
		JButton cancelButton = new JButton("CANCEL");
		cancelButton.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		cancelButton.setBackground(new Color(32, 178, 170));
		cancelButton.setFocusPainted(false);
		
		cancelButton.addActionListener(new CancelButton(this));
		cancelButton.setForeground(new Color(0, 0, 0));
		cancelButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		cancelButton.setName("cbtn");
		
		JRadioButton interestRdbn = new JRadioButton("Interested");
		interestRdbn.setFocusPainted(false);
		interestRdbn.setName("interest");
		buttonGroup_1.add(interestRdbn);
		interestRdbn.setBackground(new Color(153, 204, 204));
		interestRdbn.setForeground(new Color(102, 0, 153));
		interestRdbn.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		JRadioButton notIntersetRdbn = new JRadioButton("Not interested");
		notIntersetRdbn.setFocusPainted(false);
		notIntersetRdbn.setName("not interest");
		buttonGroup_1.add(notIntersetRdbn);
		notIntersetRdbn.setBackground(new Color(153, 204, 204));
		notIntersetRdbn.setForeground(new Color(102, 0, 153));
		notIntersetRdbn.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		JDateChooser ottDate = new JDateChooser();
		ottDate.getCalendarButton().setName("heelo");
		ottDate.setName("JDateChooserott");
		((JTextFieldDateEditor)ottDate.getDateEditor()).setEditable(false);
		
		JDateChooser theatreDate = new JDateChooser();
		theatreDate.getCalendarButton().setName("calen");
		theatreDate.setName("JDateChoosertheatre");
		((JTextFieldDateEditor)theatreDate.getDateEditor()).setEditable(false);
		
		JScrollPane genreScrollPane = new JScrollPane();
		genreScrollPane.setName("moviescroll");
		
		JButton updateButton = new JButton("UPDATE");
		updateButton.setName("update");
		updateButton.addActionListener(new MovieUpdateController(this, model));
		updateButton.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		updateButton.setBackground(new Color(32, 178, 170));
		updateButton.setFocusPainted(false);
		updateButton.setForeground(new Color(0, 0, 0));
		updateButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(HeadLabel, GroupLayout.DEFAULT_SIZE, 1020, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(106)
					.addComponent(theatreRdbn, GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
					.addGap(287)
					.addComponent(ottRdbn, GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
					.addGap(157))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(595)
					.addComponent(interestRdbn, GroupLayout.PREFERRED_SIZE, 141, Short.MAX_VALUE)
					.addGap(98)
					.addComponent(notIntersetRdbn, GroupLayout.PREFERRED_SIZE, 183, Short.MAX_VALUE)
					.addGap(3))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(40)
					.addComponent(addButton, GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
					.addGap(106)
					.addComponent(updateButton, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
					.addGap(117)
					.addComponent(cancelButton, GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
					.addGap(59))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(106)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(movieLabel, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
							.addGap(10))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(languageLabel, GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
							.addGap(26))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(genereLabel, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
							.addGap(24)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(genreScrollPane, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
							.addGap(139)
							.addComponent(imageLabel, GroupLayout.PREFERRED_SIZE, 255, Short.MAX_VALUE)
							//.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(imageButton, GroupLayout.PREFERRED_SIZE, 154, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(languageText, GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
							.addGap(71)
							.addComponent(theatreLabel, GroupLayout.PREFERRED_SIZE, 255, Short.MAX_VALUE)
							.addGap(23)
							.addComponent(theatreDate, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(movieNameText, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
							.addGap(71)
							.addComponent(ottLabel, GroupLayout.PREFERRED_SIZE, 255, Short.MAX_VALUE)
							.addGap(4)
							.addComponent(ottDate, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)))
					.addGap(9))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(HeadLabel, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addGap(45)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(theatreRdbn, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(ottRdbn, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addGap(54)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(movieLabel, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(movieNameText, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
						.addComponent(ottLabel, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
						.addComponent(ottDate, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(27)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(languageLabel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
								.addComponent(theatreLabel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(27)
							.addComponent(theatreDate, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(25)
							.addComponent(languageText, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(19)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(10)
											.addComponent(genereLabel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(5)
											.addComponent(imageButton, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(24)
									.addComponent(imageLabel, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)))
							.addGap(44)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(interestRdbn)
								.addComponent(notIntersetRdbn)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addComponent(genreScrollPane, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)))
					.addGap(14)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(updateButton, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
						.addComponent(addButton, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
						.addComponent(cancelButton, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
					.addGap(131))
		);
		
		JList<String> genreMovie = new JList<String>();
		genreMovie.setBackground(new Color(255, 240, 245));
		genreMovie.setSelectionForeground(new Color(0, 0, 0));
		genreMovie.setSelectionBackground(new Color(11, 122, 244));
		genreMovie.setSelectionModel(new DefaultListSelectionModel() {
		    @Override
		    public void setSelectionInterval(int index0, int index1) {
		        if(super.isSelectedIndex(index0)) {
		            super.removeSelectionInterval(index0, index1);
		        }
		        else {
		            super.addSelectionInterval(index0, index1);
		        }
		    }
		});
		genreMovie.setName("generemovie");
		genreMovie.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		DefaultListModel<String> listModel=new DefaultListModel<String>();
		try {
			listModel.addAll(new CURDMovie().retriveGenre(null));
			listModel.remove(0);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		genreMovie.setModel(listModel);
		genreScrollPane.setViewportView(genreMovie);
		
		setLayout(groupLayout);
		if(model!=null) {
			if(model.getReleaseType().equals("Theatre")) {
				theatreRdbn.setSelected(true);
			}
			else {
				ottRdbn.setSelected(true);
			}
			movieNameText.setText(model.getMovieName());
			languageText.setText(model.getLanguage());
			ottDate.setDate(model.getOttReleaseDate());
			theatreDate.setDate(model.getThreatreReleaseDate());
			if(model.isInterested()) {
				interestRdbn.setSelected(true);
			}
			else {
				notIntersetRdbn.setSelected(true);
			}
			for (String string : model.getGenereList()) {
				genreMovie.setSelectedValue(string, true);
			}
		}
	}
}
