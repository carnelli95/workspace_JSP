package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class QueryPreparedStatement {
	
		private static void method01(Scanner sc, Connection con) throws Exception{
			System.out.print("인구수를 입력하세요 : ");
			int population = sc.nextInt();
			PreparedStatement psmt = con.prepareStatement("select name from city where population > ?");
			psmt.setInt(1, population);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getString("name"));
			}
			rs.close();
		}
	
		private static void method02(Scanner sc, Connection con) throws Exception{
			System.out.print("국가 코드를 입력하세요 : ");
			String countryCode = sc.next();
			PreparedStatement psmt = con.prepareStatement("select name, population from city where countryCode = ?");
			psmt.setString(1, countryCode);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getString("name") + ", " + rs.getInt("population"));
			}
		}
	
		private static void method03(Scanner sc, Connection con) throws Exception{
			System.out.print("대륙을 입력하세요 : ");
			String continent = sc.next();
			PreparedStatement psmt = con.prepareStatement("select name from country where Continent = ?");
			psmt.setString(1, continent);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getString("name"));
			}
		}
	
		private static void method04(Scanner sc, Connection con) throws Exception{
			System.out.print("넓이를 입력하세요 : ");
			double area = sc.nextDouble();
			PreparedStatement psmt = con.prepareStatement("select name, surfacearea from country where surfacearea < ?");
			psmt.setDouble(1, area);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getString("name") + ", " + rs.getDouble("surfacearea"));
			}
		}
	
		private static void method05(Scanner sc, Connection con) throws Exception{
			System.out.print("지역을 입력하세요 : ");
			String district = sc.next();
			PreparedStatement psmt = con.prepareStatement("select name from city where district = ?");
			psmt.setString(1, district);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getString("name"));
			}
		}
		
		private static void method06(Scanner sc, Connection con) throws Exception {
			System.out.print("언어를 입력하세요 : ");
			String countryLanguage = sc.next();
			PreparedStatement psmt = con.prepareStatement("select c.name from country c "
					+ "										join countrylanguage cl"
					+ "										on c.code = cl.countrycode"
					+ "										where cl.language = ?");
			psmt.setString(1, countryLanguage);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getString("name"));
			}
			
		}
		
		private static void method07(Scanner sc, Connection con) throws Exception {
			System.out.println("비율을 입력하세요 : ");
			double ratio = sc.nextDouble();
			PreparedStatement psmt = con.prepareStatement("select countrycode, percentage from countrylanguage where percentage >= ?");
			psmt.setDouble(1, ratio);
			
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getString("countrycode") + ", " + rs.getDouble("percentage"));
			}
		}

		public static void main(String[] args) {
			Scanner sc = new Scanner(System.in);
			Connection con = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;
			int selectNum = -1;
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				String url = "jdbc:mysql://localhost:3306/world";
				con = DriverManager.getConnection(url, "musthave", "tiger");
				
				while(selectNum != 0) {
					System.out.println("질의 번호를 선택하시오 : ");
					selectNum = sc.nextInt();
					
					if(selectNum == 0) {
						System.out.println("프로그램을 종료합니다");
					}
					else if(selectNum == 1) {
						method01(sc, con);
					}
					else if(selectNum == 2) {
						method02(sc, con);
					}
					else if (selectNum == 3) {
						method03(sc, con);
					}
					else if (selectNum == 4) {
						method04(sc, con);
					}
					else if (selectNum == 5) {
						method05(sc, con);
					}
					else if (selectNum == 6) {
						method06(sc, con);
					}
					else if (selectNum == 7) {
						method07(sc, con);
					}
					else System.out.println("잘못된 입력입니다");
				}
			}	catch (Exception e) {
				System.out.println("연결 실패 : " + e.getMessage());
			}	finally {
				try {
					if(rs != null) rs.close();
					if(psmt != null) psmt.close();
					if(con != null) con.close();
				}	catch (Exception e) {
					System.out.println("오류 : " + e.getMessage());
				}
			}
		}

		
}
