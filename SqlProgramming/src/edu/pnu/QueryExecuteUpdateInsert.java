package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class QueryExecuteUpdateInsert {
	
	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/myfirstdb";
			con = DriverManager.getConnection(url, "musthave", "tiger");
			
//			String sql = "insert into phonebook(name, mobile) values(?,?)";
//			psmt = con.prepareStatement(sql);
//			psmt.setString(1, "홍길동");
//			psmt.setString(2, "010-1111-2222");
//			psmt.executeUpdate();
//			String sql = "insert into phonebook(name, mobile) values";
//			Statement stmt = con.createStatement();
//			stmt.executeUpdate(sql + String.format("('%s', '%s')", "홍길동", "010-1111-2222"));
			
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
