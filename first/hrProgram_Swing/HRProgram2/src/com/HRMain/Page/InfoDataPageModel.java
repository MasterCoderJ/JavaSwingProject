package com.HRMain.Page;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.HRMain.Page.domain.HRMember;

public class InfoDataPageModel extends AbstractTableModel{
	
	HRMember hrMember;
	
	List<HRMember> hrMemberList = new ArrayList<HRMember>();
	
	String [] columnName = {"등록번호","이름","주민번호","성별","부서","직급","연봉","이메일","ID","Pass","사진"};
	
	
	public int getRowCount() {
		
		return hrMemberList.size();
	}

	public int getColumnCount() {
		
		return columnName.length;
	}

	public Object getValueAt(int row, int col) {
		HRMember hrMember = hrMemberList.get(row);
		String value = null;
		
		switch(col) {
		case 0 : value = Integer.toString(hrMember.getCompany_idx());break;
		case 1 : value = hrMember.getName();break;
		case 2 : value = hrMember.getJumin();break;
		case 3 : value = hrMember.getGender();break;
		case 4 : value = hrMember.getDept();break;
		case 5 : value = hrMember.getRank();break;
		case 6 : value = hrMember.getSal();break;
		case 7 : value = hrMember.getAddress();break;
		case 8 : value = hrMember.getId();break;
		case 9 : value = hrMember.getPass();break;
		case 10 : value = hrMember.getFilename();break;
		}
		return value;
	}

	public String getColumnName(int col) {
		
		return columnName[col];
	}


}
