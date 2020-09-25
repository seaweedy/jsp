<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	$(function(){
		$('#get').click(function(){
			$('#form1').attr('method','get')	
		})
		$('#post').click(function(){
			$('#form1').attr('method','post')	
		})
	})
</script>
<style>
</style>
</head>
<body>
	<%= request.setCharacterEncoding("utf-8")%>
	<%-- 파라미터 : client 서버로 요청을 보낼 때 추가적으로 보낸 값
				ex : 로그인 요청시 사용자 id, 비밀번호
				
		파라미터는 내부적으로 Map<String, String[]> 객체로 관리를 한다.
						파라미터 이름, 파라미터 값들
						--> 동일한 이름의 파라미터를 여러개 보낼 수 있다.
						
		request 객체에 있는 파라미터 관련 메서드 4가지
		String getParameter(String param) : param에 해당하는 파라미터 값을 조회한다.
											여러개의 값이 존재시 : 파라미터맵에서 첫번째 값을 반환
		String[] getParameterValues(String param) : param에 해당하는 모든 파라미터 값을 반환한다.
		Map<String, String[]> getParameterMap() : request 객체에 생성된 파라미터 맵을
													반환
		Enumeration<String> getParameterNames() : request 객체에 답긴 모든 파라미터 이름을 반환																													
	 --%>
	 <%-- 한글 파라미터 설정
	 	get : server.xml <Connector URIEncoding = "utf-8">
	 	post : request.setCharacterEncoding("utf-8")
	 			request.getParameter()메소드를 호출하기 전에 설정 해줘야 한다.
	 
	  --%>
	 <%-- action : 요청을 보낼 경로
	 	  method : 요청 방식 (form에서는 GET,POST 두가지만 가능하며 DEFAULT는 GET
	 --%>
	 <form id = "form1" action="<%=request.getContextPath() %>/request/getReqeustResponse.jsp" method="get">
		 <label>user id : <input type = "text" id = "userId1" name = "userId" value="브라운"></label><br>
		 <label>user id : <input type = "text" id = "userId2" name = "userId" value="샐리"></label><br>
		 <label>password : <input type = "text" id = "password" name = "pass" value="pass1234"></label><br>
		 <input type = "submit" id = "subbtn" value="전송">
		 <input type="radio" class = "method" name ="method" id = "get">GET
		 <input type="radio" class = "method" name ="method" id = "post">POST
	 </form>
	 <br>
	 GET POST 두 가지를 선택 할 수 있는 라디오 버튼을 만들어서
	 FORM 전송 시 사용자가 GET, POST 방식을 지정 할 수 있도록
	 

</body>
</html>