<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% 
	int num = 3;
	String width = request.getParameter("width");
	if(width != null) {
		num = Integer.parseInt(width);
	}
	for(int k = 0; k <= num; k++) { 
		for(int i = 2; i <= 9; i++) {
			out.println(i + "단<br>");
			out.print("<br>");
			for(int j = 1; j <= 9; j++) {
				out.print(i + " x " + j  + " = " + (i * j) + "<br>");
			}
			out.print("<br>");
		}
	}
	%>
	
	<input type="text" name="width" value="<%= num %>">
	<input type="submit" readonly=readonly>
</body>
</html>