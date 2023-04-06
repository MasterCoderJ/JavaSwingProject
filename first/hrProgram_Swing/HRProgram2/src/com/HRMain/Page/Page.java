package com.HRMain.Page;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.HRMain.HRAdminMain;
import com.util.ImageManager;

public class Page extends JPanel {
	HRAdminMain hrAdminMain;
	ImageManager imageManager = new ImageManager();
	String path = "res/photo/bg.png";

	ImageIcon image = imageManager.getIcon(path, 1400, 750);

	public Page(HRAdminMain hrAdminMain) {
		this.hrAdminMain = hrAdminMain;
		this.setPreferredSize(new Dimension(1400, 750));

	}

	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		g2.drawImage(image.getImage(), 0, 0, null); 
	}

}
