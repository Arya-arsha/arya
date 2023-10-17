package com.Arya.Moviesfinder;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import com.Arya.Moviesfinder.model.Model;
import com.Arya.Moviesfinder.model.MovieModel;
import com.Arya.Moviesfinder.model.SeriesModel;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;

@SuppressWarnings("serial")
public class ImagePanel extends JPanel implements MouseListener{
	private Model model=null;
	private JButton imageBtn =null;
	private JTextArea movieLabel=null;
	
	public ImagePanel(final Model model) throws IOException {
		
		setMaximumSize(new Dimension(272, 272));
		setBorder(null);
		setAutoscrolls(true);
		setBackground(Color.black);
		setSize(new Dimension(175, 225));
		this.model=model;
		setName("moviepanel");
		setLayout(new BorderLayout(0, 0));
		movieLabel = new JTextArea();
		movieLabel.setAutoscrolls(true);
		movieLabel.setEditable(false);
		movieLabel.setLineWrap(true);
		movieLabel.setRows(3);
		

		movieLabel.setPreferredSize(new Dimension(175,20));
		if(model instanceof MovieModel) {
			movieLabel.setText(((MovieModel)model).getMovieName());
		}
		else {
			movieLabel.setText(((SeriesModel)model).getSeriesname()+" SEASON "
		                                     +((SeriesModel)model).getSeason().getSeason());
		}
		movieLabel.setName("movie name  label");
		movieLabel.setBackground(Color.BLACK);
		movieLabel.setForeground(Color.white);
		movieLabel.setFont(new Font("Arial Black", Font.BOLD, 10));
		add(movieLabel, BorderLayout.SOUTH);
		
	    imageBtn = new JButton();
	    imageBtn.setAutoscrolls(true);
	    imageBtn.setSize(new Dimension());
		imageBtn.setIcon(createImageIcon());
		imageBtn.setBorder(BorderFactory.createEmptyBorder());
		imageBtn.setContentAreaFilled(false);
		imageBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							MovieData frame = new MovieData(model);
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
							JOptionPane.showMessageDialog(null, "Couldn't load"
									,"WARNING", JOptionPane.WARNING_MESSAGE);
						}
					}
				});
			}
		});
		imageBtn.setName("movie image button");
	    imageBtn.addMouseListener(this);
		add(imageBtn, BorderLayout.CENTER);		
	}
	
	private ImageIcon createImageIcon() throws IOException {
		
		if(model.getImage()!=null) {
			
			Image poster= ImageIO.read(new BufferedInputStream( model.getImage()))
				.getScaledInstance(this.getWidth(),
						this.getHeight()-movieLabel.getHeight(),Image.SCALE_SMOOTH);
			model.getImage().reset();
			return new ImageIcon(poster);
		}
		if (model instanceof SeriesModel) {
			
			InputStream image=((SeriesModel)model).getSeason().getSeasonImage();
			if (image!=null ){
					Image poster= ImageIO.read(new BufferedInputStream(image))
							.getScaledInstance(this.getWidth(),
									this.getHeight()-movieLabel.getHeight(),Image.SCALE_SMOOTH);
					image.reset();
					return new ImageIcon(poster);
			}
		}
		URL url = getClass().getResource("/resources/no poster.jpeg");
		Image poster= ImageIO.read(url)
					.getScaledInstance(this.getWidth(),
							this.getHeight()-movieLabel.getHeight(),Image.SCALE_SMOOTH);	
		return new ImageIcon(poster);	
		}
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		setBorder(new BevelBorder(BevelBorder.RAISED, new Color(21, 234, 43), 
				new Color(21, 234, 43), new Color(255, 255, 255), new Color(255, 255, 255)));
		
	}
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		setBorder(null);
		
	}

}
