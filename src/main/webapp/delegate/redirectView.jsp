<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% List<String> rangers = (List<String>)request.getAttribute("rangers");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
redirectView.jsp
	<table border="1">
		<tr>
			<th>이름</th>
		</tr>
		<%-- requst 객체에 저장된 rangers 속성을 이용하여 tr, td 그리고 ranger 이름 출력 --%>
		<% for(int i = 0; i < rangers.size(); i++){ 
			if(rangers.size()!=0){
		%>
		<tr>
			<td><%=rangers.get(i) %></td>
		</tr>
		<% 	}else{%>
		<tr>
			<td>값이 존재 하지 않습니다.</td>
		</tr>
		<%		
			}
		}
		%>
	</table>
</body>
</html>