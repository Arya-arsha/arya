package com.Arya.Moviesfinder;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.Arya.Moviesfinder.controller.Browsecontroller;
import com.Arya.Moviesfinder.controller.SeasonAddController;
import com.Arya.Moviesfinder.controller.SeasonUpdateController;
import com.Arya.Moviesfinder.model.SeasonModel;
import com.Arya.Moviesfinder.model.SeriesModel;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.border.LineBorder;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class SeasonDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField seasonText;
	private JTextField episodeText;

	/**
	 * Launch the application.
	 */
	public SeasonDialog( SeriesModel series) {
		SeasonModel season=series.getSeason();
		setResizable(false);
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
		Dimension size=Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((int)size.getWidth()/2-266,( int)size.getHeight()/2-187, 532, 374);
		getContentPane().setLayout(new BorderLayout());
		setModal(true);
		contentPanel.setBackground(new Color(255, 228, 225));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel seasonLabel = new JLabel("Season");
		seasonLabel.setName("season label");
		seasonLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		seasonLabel.setBounds(58, 72, 149, 31);
		contentPanel.add(seasonLabel);
		
		seasonText = new JTextField(season.getSeason()+"");
		seasonText.setName("season");
		seasonText.setFont(new Font("Times New Roman", Font.BOLD, 15));
		seasonText.setBounds(249, 74, 149, 33);
		contentPanel.add(seasonText);
		seasonText.setColumns(10);
		
		JLabel episodelabel = new JLabel("Episode");
		episodelabel.setName("episode label");
		episodelabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		episodelabel.setBounds(58, 129, 149, 31);
		contentPanel.add(episodelabel);
		
		episodeText = new JTextField(season.getEpisodeNumber()+"");
		episodeText.setFont(new Font("Times New Roman", Font.BOLD, 15));
		episodeText.setName("episode");
		episodeText.setBounds(249, 130, 149, 34);
		contentPanel.add(episodeText);
		episodeText.setColumns(10);
		
		JLabel releaseLabel = new JLabel("Release Date");
		releaseLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		releaseLabel.setName("release label");
		releaseLabel.setBounds(58, 191, 149, 31);
		contentPanel.add(releaseLabel);
		
		JDateChooser releaseDate = new JDateChooser();
		releaseDate.getCalendarButton().setName("date calender");
		releaseDate.setName("release date");
		((JTextFieldDateEditor)releaseDate.getDateEditor()).setEditable(false);
		releaseDate.setBounds(249, 191, 149, 33);
		contentPanel.add(releaseDate);
		if (season.getReleaseDate()!=null) {
			releaseDate.setDate(season.getReleaseDate());
		}
		
		JLabel imageLabel = new JLabel("Image");
		imageLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		imageLabel.setName("image label");
		imageLabel.setBounds(58, 253, 149, 33);
		contentPanel.add(imageLabel);
		
		JButton btnNewButton = new JButton("BROWSE");
		btnNewButton.addActionListener(new Browsecontroller());
		btnNewButton.setName("insert");
		btnNewButton.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnNewButton.setBackground(new Color(255, 160, 122));
		btnNewButton.setFocusPainted(false);
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnNewButton.setBounds(249, 253, 149, 33);
		contentPanel.add(btnNewButton);
		
		JLabel headLabel = new JLabel("SEASON DETAILS");
		headLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		headLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		headLabel.setHorizontalAlignment(SwingConstants.CENTER);
		headLabel.setName("head label");
		headLabel.setOpaque(true);
		headLabel.setBackground(new Color(255, 160, 122));
		headLabel.setBounds(0, 0, 518, 45);
		contentPanel.add(headLabel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton addButton = new JButton("ADD");
				addButton.addActionListener(new SeasonAddController(contentPanel, series,this));
				addButton.setPreferredSize(new Dimension(100, 23));
				addButton.setName("add");
				addButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
				addButton.setFocusPainted(false);
				addButton.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
				addButton.setBackground(new Color(255, 160, 122));
				addButton.setActionCommand("OK");
				buttonPane.add(addButton);
				getRootPane().setDefaultButton(addButton);
			}
			{
				
				JButton updateButton = new JButton("UPDATE");
				updateButton.addActionListener(new SeasonUpdateController(contentPanel, series, this));
				updateButton.setName("update button");
				updateButton.setPreferredSize(new Dimension(100, 23));
				updateButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
				updateButton.setFocusPainted(false);
				updateButton.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
				updateButton.setBackground(new Color(255, 160, 122));
				buttonPane.add(updateButton);
			}
			{
				JButton cancelButton = new JButton("CANCEL");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setPreferredSize(new Dimension(100, 23));
				cancelButton.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
				cancelButton.setBackground(new Color(255, 160, 122));
				cancelButton.setName("cancel");
				cancelButton.setFocusPainted(false);
				cancelButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
