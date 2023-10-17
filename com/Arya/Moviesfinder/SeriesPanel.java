package com.Arya.Moviesfinder;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.Arya.Moviesfinder.controller.CancelButton;
import com.Arya.Moviesfinder.controller.SeriesAddController;
import com.Arya.Moviesfinder.controller.SeriesUpdateController;
import com.Arya.Moviesfinder.model.CURDMovie;
import com.Arya.Moviesfinder.model.Model;
import com.Arya.Moviesfinder.model.SeriesModel;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Font;
import java.sql.SQLException;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class SeriesPanel extends JPanel {

	
	private JTextField seriesNameText;
	private JTextField languageText;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private SeriesModel model=null;

	
	public SeriesPanel( Model model) {
		this.model=(SeriesModel)model;
		if (model==null) {
			this.model=new SeriesModel();
		}
		setBackground(new Color(153, 204, 204));
		setBounds(100, 100, 996, 649);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		JLabel seriesLabel = new JLabel("Series Name");
		seriesLabel.setName("lbl8");
		seriesLabel.setForeground(new Color(51, 51, 153));
		seriesLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
		
		JLabel languageLabel = new JLabel("Language");
		languageLabel.setName("lbl9");
		languageLabel.setForeground(new Color(51, 51, 153));
		languageLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
		
		JLabel genereLabel = new JLabel("Genre");
		genereLabel.setName("lbl10");
		genereLabel.setForeground(new Color(51, 51, 153));
		genereLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
		
		seriesNameText = new JTextField(this.model.getSeriesname());
		seriesNameText.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		seriesNameText.setName("seriesname");
		seriesNameText.setColumns(10);
		
		languageText = new JTextField(this.model.getLanguage());
		languageText.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		languageText.setName("language2");
		languageText.setColumns(10);
		
		JButton addButton = new JButton("ADD");
		addButton.setBackground(new Color(32, 178, 170));
		addButton.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		addButton.setFocusPainted(false);
		addButton.setForeground(new Color(0, 0, 0));
		addButton.setFont(new Font("Times New Roman", Font.BOLD, 25));
		addButton.addActionListener(new SeriesAddController(this,SeriesPanel.this.model));
		addButton.setName("btnad");
		
		JLabel headLabel = new JLabel("SERIES DETAILS");
		headLabel.setOpaque(true);
		headLabel.setHorizontalAlignment(SwingConstants.CENTER);
		headLabel.setName("lbl15");
		headLabel.setBackground(new Color(32, 178, 170));
		headLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		
		JButton cancelButton = new JButton("CANCEL");
		cancelButton.setBackground(new Color(32, 178, 170));
		cancelButton.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		cancelButton.setFocusPainted(false);
		cancelButton.setForeground(new Color(0, 0, 0));
		cancelButton.setFont(new Font("Times New Roman", Font.BOLD, 25));
		
		cancelButton.addActionListener(new CancelButton(this));
		
		JRadioButton interestRdbn = new JRadioButton("Interested");
		interestRdbn.setFocusPainted(false);
		interestRdbn.setName("interest");
		interestRdbn.setForeground(new Color(51, 51, 153));
		interestRdbn.setFont(new Font("Times New Roman", Font.BOLD, 25));
		interestRdbn.setOpaque(false);
		buttonGroup.add(interestRdbn);
		
		JRadioButton notInterestRdbn = new JRadioButton("Not Interested");
		notInterestRdbn.setFocusPainted(false);
		notInterestRdbn.setName("not interest");
		notInterestRdbn.setFont(new Font("Times New Roman", Font.BOLD, 25));
		notInterestRdbn.setForeground(new Color(51, 51, 153));
		notInterestRdbn.setOpaque(false);
		buttonGroup.add(notInterestRdbn);
		if (this.model.isInterested()) {
			interestRdbn.setSelected(true);
		} else {
			notInterestRdbn.setSelected(true);
		}
		
		JScrollPane genreScrollpane = new JScrollPane();
		genreScrollpane.setName("scroll");
		
		JButton updateButton = new JButton("UPDATE");
		updateButton.addActionListener(new SeriesUpdateController(this, SeriesPanel.this.model));
		updateButton.setName("update");
		updateButton.setFocusPainted(false);
		updateButton.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		updateButton.setBackground(new Color(32, 178, 170));
		updateButton.setFont(new Font("Times New Roman", Font.BOLD, 25));
		
		JButton seasonButton = new JButton("SEASON");
		seasonButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					SeasonDialog dialog = new SeasonDialog(SeriesPanel.this.model);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception f) {
					f.printStackTrace();
					JOptionPane.showMessageDialog(null, "Couldn't load"
							,"WARNING", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		seasonButton.setName("season");
		seasonButton.setFocusPainted(false);
		seasonButton.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		seasonButton.setBackground(new Color(32, 178, 170));
		seasonButton.setFont(new Font("Times New Roman", Font.BOLD, 25));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(49)
					.addComponent(seriesLabel, GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
					.addGap(794))
				.addComponent(headLabel, GroupLayout.DEFAULT_SIZE, 986, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(49)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(languageLabel, GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
							.addGap(37))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(genereLabel, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
							.addGap(129)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(genreScrollpane, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE)
							.addGap(84))
						.addGroup(Alignment.TRAILING, groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(languageText, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(seriesNameText, GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED))))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(interestRdbn, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
							.addGap(83)
							.addComponent(notInterestRdbn, GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(seasonButton, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(78)
					.addComponent(addButton, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
					.addComponent(updateButton, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
					.addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
					.addGap(65))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(headLabel, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
					.addGap(62)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(seriesLabel, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
							.addGap(20))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(seriesNameText, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
							.addGap(18)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(2)
							.addComponent(languageLabel, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(languageText, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(genereLabel, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
									.addComponent(notInterestRdbn, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
									.addComponent(interestRdbn, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)))
							.addGap(49)
							.addComponent(seasonButton, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
						.addComponent(genreScrollpane, GroupLayout.PREFERRED_SIZE, 239, GroupLayout.PREFERRED_SIZE))
					.addGap(32)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
						.addComponent(addButton, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
						.addComponent(updateButton, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE))
					.addGap(63))
		);
		
		JList<String> genreList = new JList<String>();
		genreList.setName("genre list");
		genreList.setSelectionBackground(new Color(11, 122, 244));
		genreList.setSelectionModel(new DefaultListSelectionModel() {
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
		genreList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		DefaultListModel<String> listModel=new DefaultListModel<String>();
		try {
			listModel.addAll(new CURDMovie().retriveGenre(null));
			listModel.remove(0);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		genreList.setModel(listModel);
		if (this.model.getGenereList()!=null) {
			for (String string : this.model.getGenereList()) {
				genreList.setSelectedValue(string, true);
			}
		}
		
		genreScrollpane.setViewportView(genreList);
		setLayout(groupLayout);
		
	}
}
