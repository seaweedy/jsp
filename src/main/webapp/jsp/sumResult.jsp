<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% int sumResult = (Integer)session.getAttribute("sumResult"); %>
	사이값들의 합 : <%=sumResult %>
</body>
</html>