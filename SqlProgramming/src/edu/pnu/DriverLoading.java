package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class DriverLoading {
	
	
	
//	public static String[] method1(Connection con) {
//		
//		
//		String[] cities = new String[population];
//		
//		return cities;
//	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int selectNum = -1;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/world";
			con = DriverManager.getConnection(url, "musthave", "tiger");
			st = con.createStatement();
//			rs = st.executeQuery("select id, name, countrycode, "
//							+ "district, population from city");
			
//			while(rs.next()) {
//				System.out.print(rs.getInt("id") + ", ");
//				System.out.print(rs.getString("name") + ", ");
//				System.out.print(rs.getString("countrycode") + ", ");
//				System.out.print(rs.getString("district") + ", ");
//				System.out.print(rs.getInt("population") + "\n");
//			}
			while(selectNum != 0) {
				System.out.print("질의 번호를 선택하시오 : ");
				selectNum = sc.nextInt();
				
				if(selectNum == 1) {
//					method1(con);
					System.out.print("인구수를 입력하세요 : ");
					int population = sc.nextInt();
					rs = st.executeQuery("select name from city where population>" + population);
					while(rs.next()) {
						System.out.println(rs.getString("name"));
					}
				}	
				else if(selectNum == 2) {
					System.out.print("국가 코드를 입력하세요 : ");
					String countryCode = sc.next();
					rs = st.executeQuery("select name, population from city where CountryCode='" + countryCode + "'");
					while(rs.next()) {
						System.out.println(rs.getString("name") + ", " + rs.getInt("population"));
					}
				}
				else if(selectNum == 3) {
					System.out.print("대륙을 입력하세요 : ");
					String continent = sc.next();
					rs = st.executeQuery("select name from country where Continent='" + continent + "'");
					while(rs.next()) {
						System.out.println(rs.getString("name"));
					}
				}
				else if(selectNum == 4) {
					System.out.print("넓이를 입력하세요 : ");
					double area = sc.nextInt();
					rs = st.executeQuery("select name, SurfaceArea from country where SurfaceArea<" + area);
					while(rs.next()) {
						System.out.println(rs.getString("name") + ", " + rs.getString("SurfaceArea"));
					}
				}
				
				else if(selectNum == 5) {
					
				}
				
				else System.out.println("프로그램을 종료합니다");
			}
			
		} catch (Exception e) {
			System.out.println("연결 실패 : " + e.getMessage());
		} finally {
			try {
				if (rs != null) rs.close();
				if (st != null) st.close();
				if (con != null) con.close();
			} catch (Exception e) {
				System.out.println("오류 : " + e.getMessage());
			}
		}
	}
}
