package com.HRMain.Page;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.HRMain.HRAdminMain;
import com.HRMain.Page.domain.EditMember;
import com.HRMain.Page.domain.HRMember;
import com.util.ImageManager;

public class EditPage extends Page implements ActionListener{

	JPanel p_photo;
	JLabel la_title, la_name, la_jumin, la_gender, la_position, la_mail, la_hiredate, la_id, la_pass;
	JLabel la_dept, la_rank, la_sal;
	JTextField t_jumin, t_name, t_sal, t_address, t_id, t_pass ,t_gender;
	JComboBox<String>  box_dept,  box_rank;
	JButton bt_regist, bt_prev, bt_findFile, bt_infodataPage;
	JFileChooser chooser;

	// ComboBox전용
	String[] dept = { "선택","기획실", "영업팀", "인사팀", "구매팀", "감사팀", "IT실", "마케팅팀", "해외영업부" };
	String[] rank = { "선택","사장", "부사장", "대표이사", "전무", "상무", "부장", "차장", "과장", "대리", "주임", "사원" };

	ImageManager imageManager;
	HRControlDAO hrControlDAO = new HRControlDAO();
	HRMember hrMember;
	InfoDataPage infoDataPage;
	Image image;
	
	
	public EditPage(HRAdminMain hrAdminMain) {
		super(hrAdminMain);
		
		setLayout(null);
		
		this.infoDataPage = infoDataPage;
		
		
		p_photo = new JPanel() {
			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g;
				g2.clearRect(0, 0, 300	, 400);
				g2.drawImage(image, 0, 0, 300, 400, null);
			}
		};
		
		la_title = new JLabel("사원정보 \t 수정");
		la_name = new JLabel("이름");
		la_jumin = new JLabel("주민등록번호");
		la_gender = new JLabel("성별");
		la_mail = new JLabel("주소");
		la_hiredate = new JLabel("입사일");
		la_dept = new JLabel("부서");
		la_rank = new JLabel("직급");
		la_sal = new JLabel("급여");
		la_id = new JLabel("ID");
		la_pass =new JLabel("Pass");
		
		t_jumin = new JTextField(15);
		t_name = new JTextField(15);
		t_sal = new JTextField(15);
		t_address = new JTextField(30);
		t_id = new JTextField(15);
		t_pass =new JTextField(15);
		t_gender = new JTextField(15);
		
		
		box_dept = new JComboBox<String>(dept);
		box_rank = new JComboBox<String>(rank);
		
		bt_regist = new JButton("수정완료");
		bt_prev = new JButton("이전화면");
		bt_findFile = new JButton("사진찾기");
		
		
		bt_infodataPage = new JButton("정보수정 마법사");
		
		
		//스타일 지정
		Font font = new Font("NANUM", Font.BOLD,25);
		Font font2 = new Font("NANUM", Font.BOLD, 20);
		
		la_title.setBounds(500, 20, 500, 40);
		la_title.setFont(new Font("NANUM", Font.BOLD,40));
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
		la_jumin.setForeground(Color.WHITE);;
		
		t_jumin.setBounds(220,200,150,30);
		t_jumin.setFont(font);
		
		la_gender.setBounds(160,260,70,50);
		la_gender.setFont(font);
		la_gender.setForeground(Color.WHITE);
		
		t_gender.setBounds(220,260,60,40);
		t_gender.setFont(font2);
		
		la_dept.setBounds(160,320,120,30);
		la_dept.setFont(font);
		la_dept.setForeground(Color.WHITE);
		
		box_dept.setBounds(220,320,150,30);
		box_dept.setFont(font2);
		
		la_rank.setBounds(160,380,120,30);
		la_rank.setFont(font);
		la_rank.setForeground(Color.WHITE);
		
		box_rank.setBounds(220,380,100,30);
		box_rank.setFont(font2);
		
		la_sal.setBounds(160,430,120,30);
		la_sal.setFont(font);
		la_sal.setForeground(Color.WHITE);
		
		t_sal.setBounds(220,430,200,30);
		t_sal.setFont(font2);
		
		la_mail.setBounds(160,480,120,30);
		la_mail.setFont(font);
		la_mail.setForeground(Color.WHITE);
		
		t_address.setBounds(220,480,300,30);
		t_address.setFont(font2);
		
		la_id.setBounds(160,530,120,30);
		la_id.setFont(font);
		la_id.setForeground(Color.WHITE);
		
		t_id.setBounds(220,530,150,30);
		t_id.setFont(font2);

		
		la_pass.setBounds(160,580,120,30);
		la_pass.setFont(font);
		la_pass.setForeground(Color.WHITE);
		
		t_pass.setBounds(220,580,200,30);
		t_pass.setFont(font2);


		
		p_photo.setBounds(900,100,300,400);
		p_photo.setBackground(Color.WHITE);
		
		
		bt_infodataPage.setBounds(150, 50, 300, 30);
		

		
		bt_findFile.setBounds(900, 510, 300, 40);
		bt_findFile.setFont(font2);
		
		
		bt_regist.setBounds(620, 600, 120, 40);
		bt_regist.setFont(font2);
		
		add(la_name);
		add(t_name);
		
		add(la_jumin);
		add(t_jumin);
		
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
		add(t_address);
		
		add(la_id);
		add(t_id);
		
		add(la_pass);
		add(t_pass);
		
		add(bt_findFile);
		add(bt_regist);
		
		add(bt_infodataPage);
		
		getInfo();
		
		bt_findFile.addActionListener(this);
		bt_regist.addActionListener(this);
		bt_infodataPage.addActionListener(this);
		
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj==bt_findFile) {
			
		}else if(obj==bt_regist){
			hrAdminMain.showPage(hrAdminMain.INFODATAPAGE);
			
		}else if(obj==bt_infodataPage){
			
			hrAdminMain.showPage(hrAdminMain.INFODATAPAGE);
			
		}
	}
	
	//infodataPage에서 정보 받아오기
	public void getInfo() {
//		t_name.setText(hrMember.getName());
//		System.out.println("이름"+t_name.getText());
		
	}
	
}
