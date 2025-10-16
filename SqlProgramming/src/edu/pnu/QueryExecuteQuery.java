package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class QueryExecuteQuery {
	
	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/myfirstdb";
			con = DriverManager.getConnection(url, "musthave", "tiger");
			
//			String sql = "select * from phonebook where id > ?";
//			psmt = con.prepareStatement(sql);
//			psmt.setInt(1, 0);
//			rs = psmt.executeQuery();
//			while(rs.next()) {
//				System.out.println("name : " + rs.getString("name") + ", mobile : " + rs.getString("mobile"));
//			}
			
			String sql = "select * from phonebook ";
			Statement stmt = con.createStatement();
			rs = stmt.executeQuery(sql + String.format("where id > %d", 0));
			while(rs.next()) {
				System.out.println("name : " + rs.getString("name") + ", mobile : " + rs.getString("mobile"));
			}
			
			
		
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
