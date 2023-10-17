package com.Arya.Moviesfinder;



import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.Arya.Moviesfinder.controller.WrapLayout;
import com.Arya.Moviesfinder.model.Model;


import javax.swing.JScrollPane;

import java.util.List;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.swing.ScrollPaneConstants;
import java.awt.Toolkit;
import java.awt.Dimension;

@SuppressWarnings("serial")
public class MovieList extends JFrame {

	private JPanel contentPane;
	private List<Model> models=null;
	

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public MovieList(List<Model> models){
		
		Dimension size=Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 00, (int)size.getWidth()+5,(int)size.getHeight()-30);
		setTitle("MOVIES");
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
		contentPane = new JPanel();
		contentPane.setBorder(null);
		contentPane. setBackground(Color.BLACK);
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBar(null);
		scrollPane.setBackground(Color.BLACK);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension());
		scrollPane.getVerticalScrollBar().setUnitIncrement(25);
		contentPane.add(scrollPane);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new WrapLayout(FlowLayout.LEADING,5,5));
		panel.setBackground(Color.BLACK);
		ImagePanel actionPanel=null;
		for (Model model : models) {
			try {
				actionPanel=new ImagePanel(model);
				panel.add(actionPanel);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	public List<Model> getModels() {
		return models;
	}

	public void setModels(List<Model> models) {
		this.models = models;
	}
}
