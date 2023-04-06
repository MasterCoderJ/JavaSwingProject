package com.HRMain.Page;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.HRMain.HRAdminMain;
import com.HRMain.Page.domain.HRMember;

public class LoginPage extends Page {
	
	JLabel la_id, la_pass, la_title;
	JTextField t_id;
	JPasswordField t_pass;
	JButton bt_login, bt_regist;
	
	HRControlDAO hrControlDAO;

	public LoginPage(HRAdminMain hrAdminMain) {
		super(hrAdminMain);
		
		setLayout(null);
		
		hrControlDAO = new HRControlDAO();
		
		la_title = new JLabel("HRSYSTEM");
		la_title.setFont(new Font("NANUM", Font.BOLD, 50));
		la_title.setPreferredSize(new Dimension(1400,100));
		
		Font font = new Font("NANUM", Font.BOLD, 20);
		
		la_id = new JLabel("아이디");		
		la_pass = new JLabel("비밀번호");
		t_id = new JTextField(30);
		t_pass = new JPasswordField(30);
		bt_login = new JButton("로그인");
		bt_regist = new JButton("직원등록");
		
		//스타일
		
		la_id.setFont(font);
		t_id.setFont(font);
		la_pass.setFont(font);
		t_pass.setFont(font);
		bt_login.setFont(font);
		bt_regist.setFont(font);
		
		
		la_title.setBounds(550,70, 1000, 50);
		la_title.setForeground(Color.WHITE);
		
		la_id.setBounds(400,250, 100, 100);
		la_id.setForeground(Color.WHITE);
		t_id.setBounds(490,285, 500, 40);
		
		la_pass.setBounds(400,400, 100, 100);
		la_pass.setForeground(Color.WHITE);
		t_pass.setBounds(490,435, 500, 40);
		
		bt_login.setBounds(370,650,150,50);
		bt_regist.setBounds(870,650,150,50);
		
		
		add(la_title);
		add(la_id);
		add(t_id);
		add(la_pass);
		add(t_pass);
		add(bt_login);
		add(bt_regist);	
		
		bt_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginCheck();
			}
		});
		
		bt_regist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(hrAdminMain, "관리자에게 문의해주세요");
			}
		});
	}

	//로그인시 계정 대조해서 통, 불통
	public void loginCheck() {
		HRMember hrMember = new HRMember();
		hrMember.setId(t_id.getText());
		hrMember.setPass(new String(t_pass.getPassword()));
		hrMember.setCompany_idx(1);
		
		hrMember = hrControlDAO.select(hrMember);
		if(hrMember == null) {
			JOptionPane.showMessageDialog(this, "로그인 정보가 올바르지 않습니다");
		}else {
			JOptionPane.showMessageDialog(this, "로그인 성공");
			hrAdminMain.showPage(HRAdminMain.CALLENDARPAGE);
		}
	}
}
