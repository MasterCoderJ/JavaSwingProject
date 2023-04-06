package com.HRMain.Page;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.HRMain.HRAdminMain;
import com.HRMain.Page.Cal.CalendarPageDAO;
import com.HRMain.Page.Cal.DateCell;
import com.HRMain.Page.Cal.DayCell;
import com.HRMain.Page.domain.CalendarDTO;
import com.util.DBManager;

public class CalendarPage extends Page implements ActionListener {

	CalendarPageDAO calendarPageDAO = new CalendarPageDAO();
	public CalendarDTO calendarDTO;
	DateCell dateCell;

	JPanel p_south;
	JPanel p_north;
	JPanel p_west;
	JComboBox<String> box_yy;
	JComboBox<String> box_mm;
	JComboBox<String> box_dd;
	public JTextArea area;
	JScrollPane scroll;
	JButton bt_regist;
	JButton bt_del;
	JLabel main;

	// 센터
	JPanel p_center;
	JPanel p_title;
	JPanel p_dayOfWeek;
	JPanel p_dayOfMonth;
	JButton bt_prev;
	JLabel la_title;
	JButton bt_next, bt_registPage, bt_infoDataPage, bt_infoPage;

	// 요일
	DayCell[] dayCells = new DayCell[7];
	String[] dayTitle = { "일", "월", "화", "수", "목", "금", "토" };

	// 날짜
	DateCell[][] dateCells = new DateCell[6][7];

	// 현재 시용자가 보게 될 날짜 정보
	Calendar currentObj = Calendar.getInstance();

	String str, str2;

	public CalendarPage(HRAdminMain hrAdminMain) {
		super(hrAdminMain);

		setLayout(null);

		p_north = new JPanel();
		p_west = new JPanel();
		p_south = new JPanel();
		p_center = new JPanel();

		p_north.setBounds(450, 0, 600, 70);
		p_north.setBackground(Color.BLUE);
		p_north.setOpaque(false);

		p_west.setBounds(50, 80, 200, 550);
		p_west.setBackground(Color.RED);
		p_west.setOpaque(false);

		p_center.setBounds(300, 80, 850, 550);
		p_center.setBackground(Color.CYAN);
		p_center.setOpaque(false);

		main = new JLabel("인사 \t 스케줄");
		main.setFont(new Font("Nanum", Font.BOLD, 30));
		main.setForeground(Color.WHITE);

		p_title = new JPanel();

		box_yy = new JComboBox<String>();
		box_yy.addItem("YEAR");

		box_mm = new JComboBox<String>();
		box_mm.addItem("MONTH");

		box_dd = new JComboBox<String>();
		box_dd.addItem("DAY");

		area = new JTextArea();
		scroll = new JScrollPane(area);
		bt_regist = new JButton("등록");
		bt_del = new JButton("삭제");

		p_dayOfWeek = new JPanel();
		p_dayOfMonth = new JPanel();
		bt_prev = new JButton("이전");
		la_title = new JLabel("2022년 12월");
		bt_next = new JButton("다음");
		bt_registPage = new JButton("사원등록");
		bt_registPage.setBounds(100, 20, 100, 30);
		bt_infoDataPage = new JButton("정보수정");
		bt_infoDataPage.setBounds(200, 20, 100, 30);
		bt_infoPage = new JButton("사원정보");
		bt_infoPage.setBounds(300, 20, 100, 30);

		Dimension d = new Dimension(150, 30);
		box_yy.setPreferredSize(d);
		box_mm.setPreferredSize(d);
		box_dd.setPreferredSize(d);
		area.setPreferredSize(new Dimension(150, 150));
		bt_regist.setPreferredSize(new Dimension(d));
		bt_del.setPreferredSize(new Dimension(d));

		p_center.add(p_title);
		p_center.add(p_dayOfWeek);
		p_center.add(p_dayOfMonth);

		p_title.add(bt_prev);
		p_title.add(la_title);
		p_title.add(bt_next);

		p_title.setPreferredSize(new Dimension(750, 50));
		Font font = new Font("NANUM", Font.BOLD, 18);
		bt_prev.setFont(font);
		la_title.setFont(font);
		bt_next.setFont(font);

		p_dayOfWeek.setPreferredSize(new Dimension(750, 50));
		p_dayOfMonth.setPreferredSize(new Dimension(750, 400));
		p_dayOfWeek.setBackground(Color.PINK);
		p_dayOfWeek.setLayout(new GridLayout(1, 7));
		p_dayOfMonth.setLayout(new GridLayout(6, 7));

		p_north.add(main);

		p_west.add(box_yy);
		p_west.add(box_mm);
		p_west.add(box_dd);
		p_west.add(scroll);
		p_west.add(bt_regist);
		p_west.add(bt_del);

		add(bt_registPage);
		add(bt_infoDataPage);
		add(bt_infoPage);

		add(p_north);
		add(p_west);
		add(p_south);
		add(p_center);

		createDayOfWeek();
		createDayOfMonth();
		calculate();

		bt_next.addActionListener(this);
		bt_prev.addActionListener(this);
		bt_regist.addActionListener(this);
		bt_del.addActionListener(this);
		bt_infoPage.addActionListener(this);
		bt_registPage.addActionListener(this);
		bt_infoDataPage.addActionListener(this);

	}

	public void nextMonth() {
		int mm = currentObj.get(Calendar.MONTH);

		currentObj.set(Calendar.MONTH, mm + 1);
		calculate();

	}

	public void prevMonth() {

		int mm = currentObj.get(Calendar.MONTH);

		currentObj.set(Calendar.MONTH, mm - 1);
		calculate();

	}

	public void createDayOfWeek() {
		for (int i = 0; i < dayCells.length; i++) {
			dayCells[i] = new DayCell(dayTitle[i], "", 12, 20, 20);
			p_dayOfWeek.add(dayCells[i]);
		}
	}

	public void createDayOfMonth() {

		for (int i = 0; i < dateCells.length; i++) {
			for (int a = 0; a < dateCells[i].length; a++) {
				dateCells[i][a] = new DateCell(this, "", "", 12, 40, 10);

				p_dayOfMonth.add(dateCells[i][a]);
			}
		}
	}

	public void printTitle() {
		int yy = currentObj.get(Calendar.YEAR);
		int mm = currentObj.get(Calendar.MONTH);

		str = yy + "년";
		str2 = (mm + 1) + "월";

		la_title.setText(str + str2);
		// box_yy.setText(str);
		// t_mm.setText(str2);
	}

	public int getStartDayOfWeek() {
		Calendar cal = Calendar.getInstance();
		int yy = currentObj.get(Calendar.YEAR);
		int mm = currentObj.get(Calendar.MONTH);

		cal.set(yy, mm, 1); // 1일로 조작
		int day = cal.get(Calendar.DAY_OF_WEEK);

		return day;
	}

	public int getLastDayOfMonth() {
		Calendar cal = Calendar.getInstance();

		int yy = currentObj.get(Calendar.YEAR);
		int mm = currentObj.get(Calendar.MONTH);
		cal.set(yy, mm + 1, 0);
		int date = cal.get(Calendar.DATE);

		return date;
	}

	// 날짜 출력
	public void printDate() {

		int n = 0; // 날짜 시작시점 지표
		int d = 0;// 날짜 출력변수
		for (int i = 0; i < dateCells.length; i++) {
			for (int a = 0; a < dateCells[i].length; a++) {
				DateCell cell = dateCells[i][a];
				n++;
				if (n >= getStartDayOfWeek() && d < getLastDayOfMonth()) {
					d++;// 꾹 참았다가, n이 시작요일 이상일때부터 ++
					cell.title = Integer.toString(d);
				} else {
					cell.title = "";
				}
			}
			p_dayOfMonth.repaint();
		}
	}

	public void calculate() {
		printTitle(); // 제목출력
		printDate(); // 날짜출력
		initCell();
		printLog();

	}

	public void initCell() {
		for (int i = 0; i < dateCells.length; i++) {
			for (int a = 0; a < dateCells[i].length; a++) {

				dateCells[i][a].color = Color.WHITE; // 색깔넣고
				dateCells[i][a].content = ""; // 셀에 그 내용 뜨게 하겠음

			}
		}
		p_dayOfMonth.repaint();
	}


	public void printLog() {
		int yy = currentObj.get(Calendar.YEAR);
		int mm = currentObj.get(Calendar.MONTH);
		List<CalendarDTO> calendarList = calendarPageDAO.selectAll(yy, mm);
		// System.out.println("size=" + calendarList.size());

		// 현재 월의 모든 날짜를 대상으로 반복문 수행
		for (int i = 0; i < dateCells.length; i++) {
			for (int a = 0; a < dateCells[i].length; a++) {
				if (dateCells[i][a].title.equals("") == false) { 

					// 날짜 숫자 추출하기
					int date = Integer.parseInt(dateCells[i][a].title); 

					// 불러온 데이터만큼
					for (int x = 0; x < calendarList.size(); x++) {
						CalendarDTO obj = calendarList.get(x); 

						if (date == obj.getDd()) { 
							// System.out.println("date =" + date);
							// System.out.println("obj's idx =" + obj.getNewcalendar_idx());

							dateCells[i][a].color = Color.CYAN; 
							dateCells[i][a].content = obj.getContent();
							dateCells[i][a].calendarDTO = obj;
						}
					}
				}
			}
			p_dayOfMonth.repaint();
		}

	}

	public void regist() {
		CalendarDTO d = new CalendarDTO();

		// System.out.println("호출 전 d= " +d);

		String yy = (String) box_yy.getSelectedItem();
		String mm = (String) box_mm.getSelectedItem();
		String dd = (String) box_dd.getSelectedItem();
		String content = area.getText();

		d.setYy(Integer.parseInt(yy));
		d.setMm(Integer.parseInt(mm));
		d.setDd(Integer.parseInt(dd));
		d.setContent(content);

		int result = calendarPageDAO.insert(d);

		if (result > 0) { // 성공이라면
			JOptionPane.showMessageDialog(this, "등록성공");
			printLog();
			area.setText("");
		}
	}

	public void del() {

		if (calendarDTO.getNewcalendar_idx() == 0) {
			JOptionPane.showMessageDialog(this, "선택된 일정이 없습니다");
		} else {
			int op = JOptionPane.showConfirmDialog(this, "선택한 일정을 삭제 하시겠습니까?");

			if (op == JOptionPane.OK_OPTION) {

				int result = calendarPageDAO.delete(calendarDTO.getNewcalendar_idx());
				if (result > 0) {
					JOptionPane.showMessageDialog(this, "삭제완료");
					calculate();
				}
			}
		}
		System.out.println("calendarDTO.getNewcalendar_idx =" + calendarDTO.getNewcalendar_idx());
	}

	public void setDateInfo(String title) {
		box_yy.removeAllItems();
		box_mm.removeAllItems();
		box_dd.removeAllItems();

		int yy = currentObj.get(Calendar.YEAR);
		int mm = currentObj.get(Calendar.MONTH);

		box_yy.addItem(Integer.toString(yy));
		box_mm.addItem(Integer.toString(mm + 1));
		box_dd.addItem(title);
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == bt_regist) {
			regist();
		} else if (obj == bt_next) {
			nextMonth();
		} else if (obj == bt_prev) {
			prevMonth();
		} else if (obj == bt_del) {
			del();
		} else if (obj == bt_registPage) {
			hrAdminMain.showPage(HRAdminMain.REGISTPAGE);
		} else if (obj == bt_infoDataPage) {
			hrAdminMain.showPage(HRAdminMain.INFODATAPAGE);
		} else if (obj == bt_infoPage) {
			hrAdminMain.showPage(HRAdminMain.INFOPAGE);
		}
	}

}
