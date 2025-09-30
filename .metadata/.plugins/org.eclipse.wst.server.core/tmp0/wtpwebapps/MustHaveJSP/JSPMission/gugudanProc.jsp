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
		String gugudanType = request.getParameter("gugudanType");
		String dan = request.getParameter("dan");
		if(gugudanType != null) {
			int gugudan = Integer.parseInt(gugudanType);
			int danNum = Integer.parseInt(dan);
			response.sendRedirect("Gugudan" + gugudan + ".jsp" + "?val=" + danNum);
		}
	%>
</body>
</html>