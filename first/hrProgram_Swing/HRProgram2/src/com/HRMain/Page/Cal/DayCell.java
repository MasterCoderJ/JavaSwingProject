package com.HRMain.Page.Cal;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class DayCell extends Cell {

	public DayCell(String title, String content, int fontSize, int x, int y) {
		super(title, content, fontSize, x, y);
	}
	
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, 100, 80);
		
		g2.setColor(Color.WHITE);
		Font font = new Font("NANUM",Font.BOLD, fontSize);
		g2.setFont(font);
		g2.drawString(title, x, y);

	}
	
}
