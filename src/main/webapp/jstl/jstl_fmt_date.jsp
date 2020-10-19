<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% Date now = new Date();
		request.setAttribute("now", now);
	%>

	<!-- us : mm--dd-yyyy
		 ko : yyyy. mm. dd => 2020-10-19, 2020/10/19
	 -->	
<%-- 	<fmt:setLocale value="en"/> --%>
	now : ${now }<br>
	now-format : <fmt:formatDate value="${now }"/> <br>
	now-formatDate-pattern : <fmt:formatDate value="${now }" pattern="YYYY-MM-dd"/> <br>
	now-formatDate-pattern : <fmt:formatDate value="${now }"  dateStyle="full"/> <br>
	now-formatDate-pattern : <fmt:formatDate value="${now }"  dateStyle="medium"/> <br>
	now-formatDate-pattern : <fmt:formatDate value="${now }"  dateStyle="short"/> <br><br>
	
	<%--문자 ==> 날짜 
		"2020-10-19 10:15" 문자열을 날짜 타입으로 변경
	--%>
	<% request.setAttribute("nowStr", "2020-10-19 10:15"); %><br>
	<fmt:parseDate value="${nowStr}" pattern="yyyy-MM-dd HH:mm"/><br>
	
</body>
</html>