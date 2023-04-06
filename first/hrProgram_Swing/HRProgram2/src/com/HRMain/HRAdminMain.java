package com.HRMain;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.HRMain.Page.CalendarPage;
import com.HRMain.Page.CommutePage;
import com.HRMain.Page.EditPage;
import com.HRMain.Page.InfoDataPage;
import com.HRMain.Page.InfoPage;
import com.HRMain.Page.LoginPage;
import com.HRMain.Page.Page;
import com.HRMain.Page.RegistPage;
import com.util.DBManager;
import com.util.ImageManager;

public class HRAdminMain extends JFrame {
	JPanel container;
	ImageManager imageManager;
	DBManager dbManager = DBManager.getInstance();
	
	//사용 되어질 페이지
	Page[]page = new Page[6];
	
	public static final int LOGINPAGE= 0;
	public static final int REGISTPAGE=1;
	public static final int CALLENDARPAGE= 2;
	public static final int INFOPAGE = 3;
	public static final int EDITPAGE = 4;
	public static final int INFODATAPAGE = 5;
	
	public HRAdminMain() {
		
		imageManager = new ImageManager();
		String path = "res/photo/bg.png";
		
		ImageIcon image = imageManager.getIcon(path, 1400, 750);
		
		container = new JPanel();
		
		page[0] = new LoginPage(this);
		page[1] = new RegistPage(this);
		page[2] = new CalendarPage(this);
		page[3] = new InfoPage(this);
		page[4] = new EditPage(this);
		page[5] = new InfoDataPage(this);
		
		for(int i=0; i<page.length;i++) {
			container.add(page[i]);
		}
		
		setTitle("HRSYSTEM");
		
		
		add(container);
		setSize(1400,750);
		setVisible(true);
		setLocationRelativeTo(null);
		
		showPage(LOGINPAGE);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dbManager.release(dbManager.getConnection());
				System.exit(0);
			}
		});
		
	}
	
	public void showPage(int n) {
		for(int i=0; i<page.length ;i++) {
			if(n==i) {
				page[i].setVisible(true);
			}else {
				page[i].setVisible(false);
			}
		}
	}
	
	public static void main(String[] args) {
		new HRAdminMain();
	}
	
}
