<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
<table>
<%
	try {
// 		int dan = Integer.parseInt(request.getParameter("val"));
		
		int dan = 2;
		if(dan >= 2 && dan <= 9)
		out.println("<tr>" + dan + "단 출력</tr><br><br>");
		for(int j = 1; j <= 9; j++) {
			out.print("<tr>" + dan + " x " + j  + " = " + dan * j + "</tr><br>");
		}
		out.print("<br>");
	}	catch (Exception e) {
		out.println("2 ~ 9단 까지 입력");
	}
%>
</table>	

</body>
</html>