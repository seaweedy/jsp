<%@page import="kr.or.ddit.jobs.model.JobsVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <% List<JobsVo> jobsList = (List<JobsVo>)request.getAttribute("jobsList");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Jsp</title>
<%@ include file="/layout/commonLib.jsp" %>
</head>

<body>
<%@ include file="/layout/header.jsp" %>
		<div class="row">
			
<div class="col-sm-3 col-md-2 sidebar">
	<%@ include file="/layout/left.jsp" %>	
</div><div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				

<div class="row">
	<div class="col-sm-8 blog-main">
		<h2 class="sub-header">사용자</h2>
		<div class="table-responsive">
			<table class="table table-striped">
				<tr>
					<th>이름</th>
					<th>제목</th>
					<th>최대급여</th>
					<th>최소급여</th>
					<th>생성일</th>
					<th>갱신일</th>
				</tr>
				<c:forEach items="${jobsList }" var="jobs" > <%-- jstl로 선언하지않고 바로 for문  --%>
					<tr>
						<td>${jobs.job_id }</td>
						<td>${jobs.job_title }</td>
						<td>${jobs.min_salary }</td>
						<td>${jobs.max_salary }</td>
						<td>${jobs.create_date }</td>
						<td>${jobs.update_date }</td>
					</tr>
				</c:forEach>
			</table>
		</div>

		<a class="btn btn-default pull-right">사용자 등록</a>

		<div class="text-center">
			<ul class="pagination">
				<li><a href="#">1</a></li>
				<li><a href="#">2</a></li>
				<li><a href="#">3</a></li>
				<li><a href="#">4</a></li>
				<li><a href="#">5</a></li>
			</ul>
		</div>
	</div>
</div>
	</div>
		</div>
	</div>
</body>
</html>