package com.HRMain.Page;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.HRMain.Page.domain.EditMember;
import com.HRMain.Page.domain.HRMember;
import com.util.DBManager;

public class EditPageDAO {

	DBManager dbManager = DBManager.getInstance();
	InfoDataPage infoDataPage;
	
	//사원등록시 사용
	public int insert(EditMember editMember) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		con = dbManager.getConnection();
		
		String sql = "insert into company(company_idx, name, jumin, gender, dept, rank, sal, address, id, pass, filename)";
		sql+= " values(seq_company.nextval, ?,?,?,?,?,?,?,?,?,?)"; 
		
		
		int result = 0;
		try {
			pstmt = con.prepareStatement(sql);
			
			System.out.println(pstmt);
			pstmt.setString(1, editMember.getName());
			pstmt.setString(2, editMember.getJumin());
			pstmt.setString(3, editMember.getGender());
			pstmt.setString(4, editMember.getDept());
			pstmt.setString(5 , editMember.getRank());
			pstmt.setString(6, editMember.getSal());
			pstmt.setString(7, editMember.getAddress());
			pstmt.setString(8, editMember.getId());
			String str = new String(editMember.getPass());
			pstmt.setString(9, str);
			pstmt.setString(10, editMember.getFilename());
			
			result = pstmt.executeUpdate();
			System.out.println("DAO"+result);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(pstmt);
		}
		return result;
	}

	public int delete(int company_idx) {
		int result = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		con = dbManager.getConnection();
		String sql ="delete from company where company_idx=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, company_idx);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(pstmt);
		}
		return result;
	}

	//테이블에 모든레코드 반영
	public List selectAll() {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<HRMember>list = new ArrayList<HRMember>();
		
		con = dbManager.getConnection();
		String sql = "select * from company order by company_idx asc";
		
		try {
			pstmt=con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			
			while(rs.next()) { 
				HRMember member = new HRMember(); 
	
				member.setCompany_idx(rs.getInt("company_idx"));
				member.setName(rs.getString("name"));
				member.setJumin(rs.getString("jumin"));
				member.setGender(rs.getString("gender"));
				member.setDept(rs.getString("dept"));
				member.setRank(rs.getString("rank"));
				member.setAddress(rs.getString("address"));
				member.setId(rs.getString("id"));
				member.setPass(rs.getString("pass"));
				member.setFilename(rs.getString("filename"));
				
				list.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(pstmt, rs);
		}
		return list;
	}
	
	public EditMember dataSelect(int company_idx) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		EditMember obj = null;
		
		con = dbManager.getConnection();
		String sql = "select * from company where company_idx=? ";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, obj.getCompany_idx());
			
			rs=pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(pstmt,rs);
		}
		
		
		return obj;
	}

}
