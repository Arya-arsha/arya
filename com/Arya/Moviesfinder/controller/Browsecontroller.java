package com.Arya.Moviesfinder.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.Arya.Moviesfinder.model.Model;

public class Browsecontroller implements ActionListener{
	private void browsefile()throws FileNotFoundException {
		JFileChooser fileChooser=new JFileChooser();
		FileFilter imageFilter = new FileNameExtensionFilter(
			    "Image files", ImageIO.getReaderFileSuffixes());
		fileChooser.setFileFilter(imageFilter);
		if(fileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) {

			Model.IMAGE=fileChooser.getSelectedFile();
		}
	}
	public void actionPerformed(ActionEvent e) {
		try {
			browsefile();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
