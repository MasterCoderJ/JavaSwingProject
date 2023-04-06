package com.HRMain.Page;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultTreeModel;

import com.HRMain.HRAdminMain;
import com.HRMain.Page.domain.EditMember;
import com.HRMain.Page.domain.HRMember;
import com.util.ImageManager;

public class InfoDataPage extends Page implements ActionListener {

	JLabel la_title;
	JTextField t_search;
	JPanel p_west, p_photoInfo, p_north, p_east;
	JTextArea info_area;
	JTable table;
	JScrollPane scroll;
	JButton bt_search, bt_openEdit, bt_saveExcel, bt_del;
	JButton bt_callendarPage, bt_infoPage, bt_registPage;
	InfoDataPageModel model;
	HRControlDAO hrControlDAO = new HRControlDAO();
	EditPage editPage;
	HRMember hrMember;
	ImageManager imageManager;
	Image image;
	
	
	public InfoDataPage(HRAdminMain hrAdminMain) {
		super(hrAdminMain);

		setLayout(null);

		la_title = new JLabel("정보수정 \t마법사");
		p_west = new JPanel();
		p_north = new JPanel();
		p_east = new JPanel();
		t_search = new JTextField(28);
		bt_search = new JButton("조회");
		bt_openEdit = new JButton("정보수정");
		bt_saveExcel = new JButton("Excel 저장");
		bt_del = new JButton("정보 삭제");
		p_photoInfo = new JPanel() {
			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g;
				g2.clearRect(0, 0, 150, 200);
				g2.drawImage(image, 0, 0, 150, 200, null);
			}
		};
		info_area = new JTextArea("검색결과가 나올곳");
		table = new JTable(model = new InfoDataPageModel());
		scroll = new JScrollPane(table);

		bt_callendarPage = new JButton("캘린더");
		bt_infoPage = new JButton("사원정보");
		bt_registPage = new JButton("사원등록");

		bt_callendarPage.setBounds(150, 20, 100, 30);
		bt_infoPage.setBounds(250, 20, 100, 30);
		bt_registPage.setBounds(350, 20, 100, 30);

		p_north.setBounds(450, 0, 600, 50);
		p_north.setOpaque(false);

		la_title.setPreferredSize(new Dimension(400, 50));
		la_title.setFont(new Font("NANUM", Font.BOLD, 40));
		la_title.setForeground(Color.WHITE);

		p_north.add(la_title);

		p_west.setBounds(100, 100, 400, 600);

		t_search.setPreferredSize(new Dimension(380, 30));
		bt_search.setPreferredSize(new Dimension(70, 30));
		bt_openEdit.setPreferredSize(new Dimension(340, 30));
		p_photoInfo.setPreferredSize(new Dimension(150, 200));
		p_photoInfo.setBackground(Color.BLACK);
		info_area.setPreferredSize(new Dimension(380, 270));
		bt_saveExcel.setPreferredSize(new Dimension(150, 30));
		bt_del.setPreferredSize(new Dimension(150, 30));

		p_east.setBounds(520, 100, 800, 600);
		p_east.setOpaque(false);
		p_east.setBackground(Color.BLACK);

		scroll.setPreferredSize(new Dimension(780, 650));

		p_east.add(scroll);

		p_west.add(t_search);
		p_west.add(bt_search);
		p_west.add(bt_openEdit);
		p_west.add(p_photoInfo);
		p_west.add(info_area);
		p_west.add(bt_saveExcel);
		p_west.add(bt_del);

		add(p_west);
		add(p_north);
		add(p_east);
		add(bt_callendarPage);
		add(bt_infoPage);
		add(bt_registPage);
		
		getList();
		
		bt_openEdit.addActionListener(this);
		bt_saveExcel.addActionListener(this);
		bt_search.addActionListener(this);
		bt_callendarPage.addActionListener(this);
		bt_infoPage.addActionListener(this);
		bt_registPage.addActionListener(this);
		bt_del.addActionListener(this);
		
		this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				getList();
			}
		});

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				 int row = table.getSelectedRow();

				hrMember = model.hrMemberList.get(row);

				String filepath = hrMember.getFilename();
				URL url = this.getClass().getClassLoader().getResource(filepath);
				try {
					image = ImageIO.read(url);
					image = image.getScaledInstance(150, 200, Image.SCALE_SMOOTH);

				} catch (IOException e1) {
					e1.printStackTrace();
				}
				p_photoInfo.repaint();

				if (table.getSelectedColumn() == 1) {
					String value = (String) table.getValueAt(table.getSelectedRow(), 0);

					// area에 컬럼 정보 출력
					info_area.setText("이름: " + hrMember.getName() + "\n" + "주민번호: " + hrMember.getJumin() + "\n"
							+ "성별: " + hrMember.getGender() + "\n" + "부서: " + hrMember.getDept() + "\n" + "직급: "
							+ hrMember.getRank() + "\n" + "연봉: " + hrMember.getSal() + "\n" + "이메일: "
							+ hrMember.getAddress() + "\n" + "ID: " + hrMember.getId() + "\n" + "Pass: "
							+ hrMember.getPass());
				}
			}
		});
	}

	public void delete() { 
	
			int op = JOptionPane.showConfirmDialog(this, "삭제 하시겠습니까?");
			if (op == JOptionPane.OK_OPTION) {
				int n = hrControlDAO.delete(hrMember.getCompany_idx());
				System.out.println("idx=?"+hrMember.getCompany_idx());
				if (n > 0) {
					JOptionPane.showMessageDialog(this, "삭제성공");
					getList();
				}
			}
	}

	public void getList() {
		model.hrMemberList = hrControlDAO.selectAll();
		table.updateUI();
	
	}
	
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (obj == bt_search) {

		} else if (obj == bt_openEdit) {
			hrAdminMain.showPage(hrAdminMain.EDITPAGE);
		} else if (obj == bt_saveExcel) {

		} else if (obj == bt_callendarPage) {
			hrAdminMain.showPage(hrAdminMain.CALLENDARPAGE);
		} else if (obj == bt_infoPage) {
			hrAdminMain.showPage(hrAdminMain.INFOPAGE);
		} else if (obj == bt_registPage) {
			hrAdminMain.showPage(hrAdminMain.REGISTPAGE);
		} else if (obj == bt_del) {
			delete();
		}

	}
}
