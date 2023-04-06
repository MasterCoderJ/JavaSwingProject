package com.HRMain.Page.Cal;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import com.HRMain.Page.CalendarPage;
import com.HRMain.Page.domain.CalendarDTO;

public class DateCell extends Cell {
	public CalendarDTO calendarDTO;
	public Color color = Color.WHITE;
	public CalendarPage calendarPage;

	public DateCell(CalendarPage calendarPage, String title, String content, int fontSize, int x, int y) {
		super(title, content, fontSize, x, y);

		this.calendarPage = calendarPage;

		Border border = new LineBorder(Color.DARK_GRAY);
		setBorder(border);

		this.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				calendarPage.setDateInfo(DateCell.this.title);
				if (color == Color.WHITE) {
					color = Color.YELLOW;
//					System.out.println("content="+calendarDTO.getContent());
//					System.out.println("calendarDTO ="+ calendarDTO);
//					System.out.println("select cell's dto_ idx is ="+calendarDTO.getNewcalendar_idx());
					calendarPage.calendarDTO = calendarDTO;
					
				} else {
					color = Color.WHITE;
				}
				repaint();
			}
		});

	}

	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.clearRect(0, 0, 120, 120);

		g2.setColor(color);
		g2.fillRect(0, 0, 120, 120);

		g2.setColor(Color.BLACK);
		g2.drawString(title, x, y);
		g2.drawString(content, x - 30, y + 20);
	}

}
