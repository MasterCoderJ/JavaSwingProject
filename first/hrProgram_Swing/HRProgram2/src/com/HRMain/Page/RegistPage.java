package com.HRMain.Page;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.HRMain.HRAdminMain;
import com.HRMain.Page.domain.HRMember;
import com.util.ImageManager;

public class RegistPage extends Page implements ActionListener {
	HRControlDAO hrControlDAO = new HRControlDAO();
	HRMember hrMember;
	
	JPanel p_photo;
	JLabel la_title, la_name, la_jumin, la_gender, la_position, la_mail, la_hiredate, la_id, la_pass;
	JLabel la_dept, la_rank, la_sal;
	JTextField t_jumin, t_jumin2, t_name, t_sal, t_mail, t_id, t_pass, t_gender;
	JComboBox<String> box_dept, box_rank;
	JButton bt_regist, bt_prev, bt_findFile, bt_callendar, bt_info, bt_edit, bt_jumin;
	JFileChooser chooser;

	// ComboBox전용
	String[] dept = { "선택","기획실", "영업팀", "인사팀", "구매팀", "감사팀", "IT실", "마케팅팀", "해외영업부" };
	String[] rank = { "선택","사장", "부사장", "대표이사", "전무", "상무", "부장", "차장", "과장", "대리", "주임", "사원" };

	ImageManager imageManager;
	InfoDataPage infoDataPage;
	
	ImageIcon img;
	
	String imgPath;

	public RegistPage(HRAdminMain hrAdminMain) {
		super(hrAdminMain);

		setLayout(null);

		chooser = new JFileChooser("D:/StudyHistory/JavaSave/HRProgram/src/res/photo/"); //경로 설정 바꿔 주기
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		imageManager = new ImageManager();

		p_photo = new JPanel(){
			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D)g;
				g2.setBackground(Color.WHITE);
				
				if(img!=null) {
					g2.clearRect(0, 0, 300	, 400);
					g2.drawImage(img.getImage(), 0, 0, 300, 400, null);
				}else {
					g2.clearRect(0, 0, 300, 400);
				}
			}
		};

		la_title = new JLabel("사원등록");
		la_name = new JLabel("이름");
		la_jumin = new JLabel("주민등록번호");
		la_gender = new JLabel("성별");
		la_mail = new JLabel("e-mail");
		la_hiredate = new JLabel("입사일");
		la_dept = new JLabel("부서");
		la_rank = new JLabel("직급");
		la_sal = new JLabel("급여");
		la_id = new JLabel("ID");
		la_pass = new JLabel("Pass");

		t_jumin = new JTextField(15);
		t_jumin.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				JTextField src = (JTextField) e.getSource();
				if (src.getText().length() >= 6)
					e.consume();
			}
		});

		t_jumin2 = new JTextField(15);
		t_jumin2.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				JTextField src = (JTextField) e.getSource();
				if (src.getText().length() >= 7) {
					e.consume();
				}
			}
		});

		t_name = new JTextField(15);
		t_sal = new JTextField(15);
		t_mail = new JTextField(30);
		t_id = new JTextField(15);
		t_pass = new JTextField(15);

		t_gender = new JTextField(10);
		box_dept = new JComboBox<String>(dept);
		box_rank = new JComboBox<String>(rank);

		bt_regist = new JButton("사원등록");
		bt_prev = new JButton("이전화면");
		bt_findFile = new JButton("사진찾기");

		bt_callendar = new JButton("캘린더");
		bt_info = new JButton("인사정보");
		bt_edit = new JButton("정보수정");
		bt_jumin = new JButton("확인");

		// 스타일 지정
		Font font = new Font("NANUM", Font.BOLD, 25);
		Font font2 = new Font("NANUM", Font.BOLD, 20);

		la_title.setBounds(500, 20, 500, 40);
		la_title.setFont(new Font("NANUM", Font.BOLD, 40));
		la_title.setForeground(Color.WHITE);
		la_title.setHorizontalAlignment(SwingConstants.CENTER);

		add(la_title);

		la_name.setBounds(150, 150, 150, 30);
		la_name.setFont(font);
		la_name.setForeground(Color.WHITE);
		t_name.setBounds(220, 150, 150, 30);
		t_name.setFont(font);

		la_jumin.setBounds(50, 200, 200, 30);
		la_jumin.setFont(font);
		la_jumin.setForeground(Color.WHITE);
		;

		t_jumin.setBounds(220, 200, 120, 30);
		t_jumin.setFont(font);
		t_jumin2.setBounds(350, 200, 120, 30);
		t_jumin2.setFont(font);
		
		bt_jumin.setBounds(480, 200, 60, 30);

		la_gender.setBounds(160, 260, 70, 50);
		la_gender.setFont(font);
		la_gender.setForeground(Color.WHITE);

		t_gender.setBounds(220, 260, 60, 40);
		t_gender.setFont(font2);

		la_dept.setBounds(160, 320, 120, 30);
		la_dept.setFont(font);
		la_dept.setForeground(Color.WHITE);

		box_dept.setBounds(220, 320, 150, 30);
		box_dept.setFont(font2);

		la_rank.setBounds(160, 380, 120, 30);
		la_rank.setFont(font);
		la_rank.setForeground(Color.WHITE);

		box_rank.setBounds(220, 380, 100, 30);
		box_rank.setFont(font2);

		la_sal.setBounds(160, 430, 120, 30);
		la_sal.setFont(font);
		la_sal.setForeground(Color.WHITE);

		t_sal.setBounds(220, 430, 200, 30);
		t_sal.setFont(font2);

		la_mail.setBounds(140, 480, 120, 30);
		la_mail.setFont(font);
		la_mail.setForeground(Color.WHITE);

		t_mail.setBounds(220, 480, 300, 30);
		t_mail.setFont(font2);

		la_id.setBounds(160, 530, 120, 30);
		la_id.setFont(font);
		la_id.setForeground(Color.WHITE);

		t_id.setBounds(220, 530, 150, 30);
		t_id.setFont(font2);

		la_pass.setBounds(160, 580, 120, 30);
		la_pass.setFont(font);
		la_pass.setForeground(Color.WHITE);

		t_pass.setBounds(220, 580, 200, 30);
		t_pass.setFont(font2);

		p_photo.setBounds(900, 100, 300, 400);
		p_photo.setBackground(Color.WHITE);

		bt_callendar.setBounds(50, 50, 100, 30);
		bt_info.setBounds(150, 50, 100, 30);
		bt_edit.setBounds(250, 50, 100, 30);

		bt_findFile.setBounds(900, 510, 300, 40);
		bt_findFile.setFont(font2);

		bt_regist.setBounds(620, 600, 120, 40);
		bt_regist.setFont(font2);

		add(la_name);
		add(t_name);

		add(la_jumin);
		add(t_jumin);
		add(t_jumin2);

		add(la_gender);
		add(t_gender);

		add(la_dept);
		add(box_dept);

		add(la_rank);
		add(box_rank);

		add(la_sal);
		add(t_sal);

		add(p_photo);

		add(la_mail);
		add(t_mail);

		add(la_id);
		add(t_id);

		add(la_pass);
		add(t_pass);

		add(bt_jumin);
		add(bt_findFile);
		add(bt_regist);
		add(bt_callendar);
		add(bt_info);
		add(bt_edit);
		
		bt_findFile.addActionListener(this);
		bt_regist.addActionListener(this);
		bt_callendar.addActionListener(this);
		bt_info.addActionListener(this);
		bt_edit.addActionListener(this);
		bt_jumin.addActionListener(this);

	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (obj == bt_findFile) {
			chooseFile();
		} else if (obj == bt_regist) {
			regist();
		} else if (obj == bt_callendar) {

			hrAdminMain.showPage(hrAdminMain.CALLENDARPAGE);

		} else if (obj == bt_info) {

			hrAdminMain.showPage(hrAdminMain.INFOPAGE);

		} else if (obj == bt_edit) {
			hrAdminMain.showPage(hrAdminMain.INFODATAPAGE);
			
		}else if (obj == bt_jumin) {
			fillGender();
		}
	}
	
	public void fillGender() {
		
		t_gender.setEnabled(false);
		
		char gender = t_jumin2.getText().charAt(0);
		
		if(gender =='1' || gender =='3') {
			t_gender.setText("남");
			JOptionPane.showMessageDialog(this, "검증완료");

		}else if(gender == '2' || gender =='4') {
			t_gender.setText("여");
			JOptionPane.showMessageDialog(this, "검증완료");
		}else {
			JOptionPane.showMessageDialog(this, "올바르지 않는 형식입니다");
		}
	}


	public void chooseFile() {
		
		int result = chooser.showOpenDialog(this); 
		
		if (result == chooser.APPROVE_OPTION) {
			
			File file = chooser.getSelectedFile();
			
			imgPath = "res/photo/"+ file.getName();
			
			img = imageManager.getIcon(imgPath, 250, 300);
			
			p_photo.repaint();
		}
	}

	public void regist() { 
		HRMember hrMember = new HRMember();
		
		hrMember.setName(t_name.getText());

		String jumin = t_jumin.getText();
		String jumin2 = t_jumin2.getText();
		hrMember.setJumin(jumin +"-"+ jumin2);

		
		String obj = (String) box_dept.getSelectedItem();
		String obj2 = (String) box_rank.getSelectedItem();
		hrMember.setGender(t_gender.getText());
		
		hrMember.setDept(obj);
		
		hrMember.setRank(obj2);
		
		hrMember.setSal(t_sal.getText());
		
		hrMember.setAddress(t_mail.getText());
		
		hrMember.setId(t_id.getText());
		
		hrMember.setPass(new String(t_pass.getText()));
		hrMember.setFilename(imgPath);
		
		
		int result = hrControlDAO.insert(hrMember);

		if (result > 0) {
			JOptionPane.showMessageDialog(this, "등록 완료!");
			clear();
			
		}

	}
	
	public void clear() {
		t_name.setText("");
		t_jumin.setText("");
		t_jumin2.setText("");
		t_gender.setText("");
		t_sal.setText("");
		t_mail.setText("");
		t_id.setText("");
		t_pass.setText("");
		box_dept.setSelectedIndex(0);
		box_rank.setSelectedIndex(0);
		img=null;
		p_photo.repaint();
	}

}
