package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DriverLoading {
    
    private static void method01(Scanner sc, Statement st) throws Exception {
        System.out.print("인구수를 입력하세요 : ");
        int population = sc.nextInt();
        ResultSet rs = st.executeQuery("select name from city where population > " + population);
        while (rs.next()) {
            System.out.println(rs.getString("name"));
        }
        rs.close();
    }
    
    private static void method02(Scanner sc, Statement st) throws Exception {
        System.out.print("국가 코드를 입력하세요 : ");
        String countryCode = sc.next();
        ResultSet rs = st.executeQuery("select name, population from city where CountryCode='" + countryCode + "'");
        while (rs.next()) {
            System.out.println(rs.getString("name") + ", " + rs.getInt("population"));
        }
        rs.close();
    }
    
    private static void method03(Scanner sc, Statement st) throws Exception {
        System.out.print("대륙을 입력하세요 : ");
        String continent = sc.next();
        ResultSet rs = st.executeQuery("select name from country where Continent='" + continent + "'");
        while (rs.next()) {
            System.out.println(rs.getString("name"));
        }
        rs.close();
    }
    
    private static void method04(Scanner sc, Statement st) throws Exception {
        System.out.print("넓이를 입력하세요 : ");
        double area = sc.nextDouble();
        ResultSet rs = st.executeQuery("select name, SurfaceArea from country where SurfaceArea < " + area);
        while (rs.next()) {
            System.out.println(rs.getString("name") + ", " + rs.getString("SurfaceArea"));
        }
        rs.close();
    }
    
    private static void method05(Scanner sc, Statement st) throws Exception {
        System.out.println("");
    }
    
//    class CountryDTO {
//    	String code;
//    	String name;
//    	int population;
//    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        int selectNum = -1;
        
        try {
        	// Driver Loading
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/world";
            // Get Connection
            con = DriverManager.getConnection(url, "musthave", "tiger");
            // Create Statement
            st = con.createStatement();
//            ResultSet rs2 = st.executeQuery("select code, name, population from country limit 10");
//            
//            List<CountryDTO> list = new ArrayList<>();
//            while(rs.next()) {
//            	CountryDTO dto = new CountryDTO();
//            	dto.code = rs.getString(1);
//            }
            
            while (selectNum != 0) {
                System.out.print("질의 번호를 선택하시오: ");
                selectNum = sc.nextInt();
                
                if (selectNum == 1) {
                    method01(sc, st);
                } else if (selectNum == 2) {
                	method02(sc, st);
                } else if (selectNum == 3) {
                	method03(sc, st);
                } else if (selectNum == 4) {
                	method04(sc, st);
                } else if (selectNum == 5) {
                	method05(sc, st);
                } else if (selectNum != 0) {
                    System.out.println("잘못된 입력입니다.");
                }
            }
            System.out.println("프로그램을 종료합니다.");
            
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
