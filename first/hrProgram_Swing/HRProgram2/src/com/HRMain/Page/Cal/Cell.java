package com.HRMain.Page.Cal;

import javax.swing.JPanel;

public class Cell extends JPanel{
	
	public String title; 
	public String content;
	int fontSize;
	int x;
	int y;
	
	public Cell(String title, String content, int fontSize, int x, int y) {
		this.title = title;
		this.content = content;
		this.fontSize = fontSize;
		this.x = x;
		this.y= y;
	}
}
