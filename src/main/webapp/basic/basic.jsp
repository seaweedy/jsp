<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> <!-- 디렉티브 jps설정  -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	구구단
	<% // 스크립틀릿 : 자바 로직을 작성하는 공간
		Date date = new Date();
	%>
	
	<!-- expression : 화면에 출력을 해준다. -->
	현재시간 : <%= date  %>

</body>
</html>