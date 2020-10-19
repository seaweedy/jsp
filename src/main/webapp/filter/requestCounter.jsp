<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<table border="1">
		<tr>
			<th>uri</th>
			<th>count</th>
		</tr>
		<tbody>
			<c:forEach items="${requestCounterMap }" var="req">
				<tr>
					<td>${req.key }</td>
					<td>${req.value }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>