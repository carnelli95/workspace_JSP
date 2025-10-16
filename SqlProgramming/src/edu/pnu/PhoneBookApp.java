package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class PhoneBookApp {
	private static Scanner sc = new Scanner(System.in);
	private static String url = "jdbc:mysql://localhost:3306/myfirstdb";
	
	
	
	private static void insertPhonebook(Connection con) {
		String sql = "insert into phonebook(name, mobile) values(?, ?)";
		
		System.out.print("이름을 입력하세요 : ");
		String name = sc.next();
		System.out.print("휴대폰 전화번호를 입력하세요 : ");
		String phoneNum = sc.next();
		try {
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, name);
			psmt.setString(2, phoneNum);
			psmt.executeUpdate();
			System.out.println("입력되었습니다");
		} catch (Exception e) {
			System.out.println("오류 : " + e.getMessage());
		}
	}
	private static void updatePhonebook(Connection con) {
		String sql = "update phonebook set home=? where id=?";
		
		System.out.print("수정할 인덱스를 입력하세요 : ");
		int index = sc.nextInt();
		System.out.println("수정할 전화번호를 입력하세요 : ");
		String homeNum = sc.next();
		try {
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, homeNum);
			psmt.setInt(2, index);
			psmt.executeUpdate();
			System.out.println("수정되었습니다");
		} catch (Exception e) {
			System.out.println("오류 : " + e.getMessage());
		}
	}
	private static void deletePhonebook(Connection con) {
		String sql = "delete from phonebook where id=?";
		
		System.out.println("삭제할 인덱스를 입력하세요 : ");
		int index = sc.nextInt();
		try {
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setInt(1, index);
			psmt.executeUpdate();
			System.out.println("삭제되었습니다");
		} catch (Exception e) {
			System.out.println("오류 : " + e.getMessage());
		}
	}
	private static void selectAllPhonebook(Connection con) {
		String sql = "select * from phonebook";
		try {
			PreparedStatement psmt = con.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				System.out.println("이름 : " + rs.getString("name") + ", 번호 : " + rs.getString("mobile"));
			}
		} catch(Exception e) {
			System.out.println("오류 : " + e.getMessage());
		}
	}
	private static void nativeQuery(Connection con) {
		sc.nextLine();
		System.out.print("SQL문을 입력하세요 : ");
		String sql = sc.nextLine();
		String pre = sql.substring(0, 6).toLowerCase();
		try {
			Statement stmt = con.createStatement();
			if(!pre.startsWith("select")) {
				int cnt = stmt.executeUpdate(sql);
				if(pre.startsWith("insert")) System.out.println(cnt + "건이 입력되었습니다.");
				else if(pre.startsWith("update")) System.out.println(cnt + "건이 수정되었습니다.");
				else if(pre.startsWith("delete")) System.out.println(cnt + "건이 삭제되었습니다");
				return;
			}
			ResultSet rs = stmt.executeQuery(sql);
			ResultSetMetaData md = rs.getMetaData();
			while(rs.next()) {
				for(int i = 1; i <= md.getColumnCount(); i++) {
					if(i != 1) System.out.println(", ");
					System.out.print(rs.getString(i));
				}
				System.out.println();
			}
		} catch (Exception e) {
			System.out.println("오류 : " + e.getMessage());
		}
		
	}
	
	
	
	public static void main(String[] args) throws SQLException {
		Connection con = DriverManager.getConnection(url, "musthave", "tiger");
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		boolean flag = true;
		
		try {
			while(flag) {
				 System.out.print("[I]nsert/[U]pdate/[D]elete/[S]elect/[N]ative/[Q]uit:");
				 char c = sc.next().toUpperCase().charAt(0);
				 switch(c) {
				 case 'I' : insertPhonebook(con);		break;
				 case 'U' : updatePhonebook(con);		break;
				 case 'D' : deletePhonebook(con);		break;
				 case 'S' : selectAllPhonebook(con);	break;
				 case 'N' : nativeQuery(con);			break;
				 case 'Q' : flag = false;				break;
				 }
				 
			}
		}	finally {
			try {
				if(rs != null) rs.close();
				if(psmt != null) psmt.close();
				if(con != null) con.close();
			} catch (Exception e) {
				System.out.println("오류 : " + e.getMessage());
			}
		System.out.println("프로그램을 종료합니다");
			}
	}
}

