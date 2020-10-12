<%@page import="kr.or.ddit.jobs.model.JobsVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% List<JobsVo> jobsList = (List<JobsVo>)request.getAttribute("jobsList");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<tr>
			<td>job_id</td>
			<td>job_title</td>
		</tr>
		<%if(jobsList !=null){
			for(int i = 0; i < jobsList.size(); i++){
		%>
		<tr>
			<td><%=jobsList.get(i).getJob_id() %></td>
			<td><%=jobsList.get(i).getJob_title() %></td>
		</tr>
		<%
			}
		}else{
		%>
		<tr>
			<td>값이 없습니다</td>	
			<td>값이 없습니다</td>
		</tr>	
		<%
		}
		%>	
		
	</table>
</body>
</html>