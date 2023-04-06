package com.HRMain.Page.Cal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.HRMain.Page.domain.CalendarDTO;
import com.util.DBManager;

public class CalendarPageDAO {
	DBManager dbManager = DBManager.getInstance();

	public int insert(CalendarDTO calendar) {

		Connection con = null;
		PreparedStatement pstmt = null;

		con = dbManager.getConnection();

		String sql = "insert into newcalendar(newcalendar_idx , yy , mm, dd, content)";
		sql += " values(seq_newcalendar.nextval, ?, ?, ? ,?)";

		int result = 0;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, calendar.getYy());
			pstmt.setInt(2, calendar.getMm());
			pstmt.setInt(3, calendar.getDd());
			pstmt.setString(4, calendar.getContent());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release(pstmt);
		}

		return result;
	}

	public List selectAll(int yy, int mm) {
		ArrayList<CalendarDTO> list = new ArrayList<CalendarDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from newcalendar where yy=? and mm=? order by newcalendar_idx asc"; // where yy=현재yy and

		try {
			pstmt = dbManager.getConnection().prepareStatement(sql);
			pstmt.setInt(1, yy);
			pstmt.setInt(2, mm + 1);
			rs = pstmt.executeQuery(); // 쿼리 수행 및 테이블 반환
			// rs는 여기서 죽어야하니까 죽이고, 컬렉션프레임웍(중 array list)+DTO로 대체해서 메인으로 넘기자.
			// DTO를 배열로 테이블로 만들어서 rs의 한칸 한칸을 담아줄거기때문에, 총 레코드를 얻기위해 rs의 last를 알 필요가 없다.

			while (rs.next()) {

				CalendarDTO calendar = new CalendarDTO();
				calendar.setNewcalendar_idx(rs.getInt("newcalendar_idx"));
				calendar.setYy(rs.getInt("yy"));
				calendar.setMm(rs.getInt("mm"));
				calendar.setDd(rs.getInt("dd"));
				calendar.setContent(rs.getString("content"));

				//System.out.println(calendar);
				// 채워진 DTO를 ArrayList에 추가하자
				list.add(calendar);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release(pstmt, rs);
		}
		//System.out.println(list);
		return list;

	}
	
	//레코드 한건
	public CalendarDTO select(int newcalendar_idx) {
		
		CalendarDTO calendar = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		con = dbManager.getConnection();
		String sql = "select * from newcalendar where newcalendar_idx=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, newcalendar_idx);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				calendar =new CalendarDTO();
				calendar.setNewcalendar_idx(rs.getInt("newcalendar_idx"));
				calendar.setYy(rs.getInt("yy"));
				calendar.setMm(rs.getInt("mm"));
				calendar.setDd(rs.getInt("dd"));
				calendar.setContent(rs.getString("content"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(pstmt, rs);
		}
		return calendar;
	}
	
	//삭제
	public int delete(int newcalendar_idx) {
		Connection con =null;
		PreparedStatement pstmt =null;
		int result = 0;
		
		con = dbManager.getConnection();
		String sql ="delete newcalendar where newcalendar_idx=?"; 
		try {
			pstmt =con.prepareStatement(sql);
			pstmt.setInt(1, newcalendar_idx);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(pstmt);
		}
		
		return result;
	}


}
