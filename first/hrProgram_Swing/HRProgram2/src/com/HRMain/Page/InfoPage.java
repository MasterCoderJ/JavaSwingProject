package com.HRMain.Page;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import com.HRMain.HRAdminMain;

//JTree활용 

public class InfoPage extends Page implements ActionListener, TreeSelectionListener{
	
	JPanel p_north, p_west, p_east, p_south;
	JLabel la_title;
	JButton bt_callendarPage, bt_registPage, bt_infoDataPage;	
	

	JTree tree;
	JScrollPane treeScroll;
	JLabel label;
	DefaultTreeModel model;
	

	public InfoPage(HRAdminMain hrAdminMain) {
		super(hrAdminMain);
		
		setLayout(null);
		
		p_north = new JPanel();
		p_west = new JPanel();
		p_south = new JPanel();
		p_east = new JPanel();
		la_title = new JLabel("인사 \t정보");
		
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("자바물산");
		DefaultMutableTreeNode child1 = new DefaultMutableTreeNode("기획실");
		DefaultMutableTreeNode child2 = new DefaultMutableTreeNode("영업팀");
		DefaultMutableTreeNode child3 = new DefaultMutableTreeNode("인사팀");
		DefaultMutableTreeNode child4 = new DefaultMutableTreeNode("구매팀");
		DefaultMutableTreeNode child5 = new DefaultMutableTreeNode("감사팀");
		DefaultMutableTreeNode child6 = new DefaultMutableTreeNode("IT실");
		DefaultMutableTreeNode child7 = new DefaultMutableTreeNode("마케팅팀");
		DefaultMutableTreeNode child8 = new DefaultMutableTreeNode("해외영업부");
		
		root.add(child1);
		root.add(child2);
		root.add(child3);
		root.add(child4);
		root.add(child5);
		root.add(child6);
		
		tree = new JTree();
		model = new DefaultTreeModel(root);
		tree.setModel(model);
		
		
		bt_callendarPage= new JButton("캘린더");
		bt_registPage= new JButton("사원등록");
		bt_infoDataPage =new JButton("정보수정");
		
		tree.setPreferredSize(new Dimension(150,450));
		
		la_title.setPreferredSize(new Dimension(400,50));
		la_title.setFont(new Font("NANUM",Font.BOLD,40));
		la_title.setForeground(Color.WHITE);
		
		p_north.setBounds(450,0,600,50);
		p_north.setOpaque(false);
		p_north.add(la_title);
		
		p_west.setBounds(150, 70, 200,550);
		p_west.setBackground(Color.BLACK);
		p_west.add(tree);
		
		p_east.setBounds(400,70,800,550);
		
		p_south.setBounds(400,620,800,100);
		p_south.setBackground(Color.GREEN);
		
		bt_callendarPage.setBounds(150, 20, 100, 30);
		bt_registPage.setBounds(250, 20, 100, 30);
		bt_infoDataPage.setBounds(350, 20, 100, 30);

		
		add(p_north);
		add(p_west);
		add(p_east);
		add(p_south);
		
		add(bt_callendarPage);
		add(bt_registPage);
		add(bt_infoDataPage);
		
		bt_callendarPage.addActionListener(this);
		bt_registPage.addActionListener(this);
		bt_infoDataPage.addActionListener(this);
		
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == bt_registPage) {
			hrAdminMain.showPage(hrAdminMain.REGISTPAGE);
		}else if(obj == bt_callendarPage) {
			hrAdminMain.showPage(hrAdminMain.CALLENDARPAGE);
		}else if(obj == bt_infoDataPage) {
			hrAdminMain.showPage(hrAdminMain.INFODATAPAGE);
		}
		
	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		DefaultMutableTreeNode node; 
		node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
		
		if(node == null) return;

		String nodeName = (String) node.getUserObject(); 
		label.setText("선택된 노드 : " + nodeName);
		
	}

}
