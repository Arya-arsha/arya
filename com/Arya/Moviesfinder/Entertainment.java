package com.Arya.Moviesfinder;

import java.awt.EventQueue;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;

@SuppressWarnings("serial")
public class Entertainment extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Entertainment frame = new Entertainment();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Couldn't load"
							,"WARNING", JOptionPane.WARNING_MESSAGE);
					
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Entertainment() {
		
		
		setTitle("ENTERTAINMENT");
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension size=Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(00, 00, (int)size.getWidth(),(int)size.getHeight()-40);
		
		contentPane = new JPanel();
		contentPane.setName("pnle");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setSize(new Dimension(1033, 876));
		scrollPane.setName("sclpnl");
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		EntertainmentPanel movieEntertainments=new EntertainmentPanel();
		
		scrollPane.setViewportView(movieEntertainments);
		contentPane.add(scrollPane);
		
	}

}
