<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel = "stylesheet" href="<%=request.getContextPath() %>/css/public.css">
</head>
<body>
	<%-- 자바언어의 철칙(정적인 언어)
		변수를 선언하지않고 사용 할 수 없다.
		객체를 생성하지않고 사용 할 수 없다.
		 --%>
		 
	<%
	%>
	<table border = "1">
		<tr>
			<td> request.getServerName() </td>
			<td><%= request.getServerName() %></td>
		</tr>
		
		<tr>
			<td> request.getServerPort()</td>
			<td><%= request.getServerPort() %></td>
		</tr>
		
		<tr>
			<td>request.getRequestURI()</td>
			<td><%= request.getRequestURI() %></td>
		</tr>
		
		<tr>
			<td>request.getMethod()</td>
			<td><%= request.getMethod() %></td>
		</tr>
		
		<tr>
			<td>request.getContextpath()</td>
			<td><%= request.getContextPath() %></td>
		</tr>
		
		
		
		
	</table>
</body>
</html>