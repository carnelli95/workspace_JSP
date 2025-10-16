package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class QueryExecuteUpdateUpdate {
	
	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/myfirstdb";
			con = DriverManager.getConnection(url, "musthave", "tiger");
			
//			String sql = "update phonebook set home=? where id=?";
//			psmt = con.prepareStatement(sql);
//			psmt.setString(1, "051-1111-1111");
//			psmt.setInt(2, 2);
//			psmt.executeUpdate();
			
			String sql = "update phonebook set ";
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sql + String.format("home='%s' where id=%d", "051-111-1111", 3));
		
		} catch (Exception e) {
			System.out.println("연결 실패 : " + e.getMessage());
		} finally {
			try {
				if(rs != null) rs.close();
				if(psmt != null) psmt.close();
				if(con != null) con.close();
			} catch(Exception e) {
			System.out.println("오류 : " + e.getMessage());
			}
		}
	}
}
