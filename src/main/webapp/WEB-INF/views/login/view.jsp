<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
<!--     <link rel="icon" href="../../favicon.ico"> -->

    <title>Signin Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/css/signin.css" rel="stylesheet">
    <script type ="text/javascript" src="${pageContext.request.contextPath}/js/js.cookie-2.2.1.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>
    	function getCookieValue(cookieName){ // 쿠키 값을 가져오는 메서드
        	cookies = document.cookie.split("; ")
        	for(i = 0; i < cookies.length; i ++){
				cookie = cookies[i].split("=")
				if(cookie[0]==cookieName){
					return cookie[1]
				}
// 				for(j = 0; j< cookie.length; j++){
// 					if(cookie[j] == cookieName){
// 						return cookie[j+1]
// 					}
// 				}
        	}
        	return "";	
        }
        
        function setCookie(cookieName, cookieValue, expires){ // expires는 현재 날짜에서 며칠 뒤 
			var today = new Date(); // expires를 위한 객체생성
			// 현재 날짜에서 미래로 + expires 만큼 더한 날짜 구하기
			today.setDate(today.getDate() + expires);
			document.cookie = cookieName + "=" + cookieValue + "; path=/; expires=" + today.toGMTString();
			console.log(document.cookie);
        }

        function deleteCookie(cookieName){
            // 해당쿠키의 expires속성을 과거 날짜로 변경
			setCookie(cookieName,"",-1) // 쿠키 설정 메서드를 호출하여 -1 대입
        }

        $(function(){
            if(Cookies.get("REMEMBERME")=="Y"||Cookies.get("REMEMBERME")=="y"){
//         	if(getCookieValue("REMEMBERME")=="Y"||getCookieValue("REMEMBERME")=="y"){ // REMEMBERME가 Y일때 
//             	var userid = getCookieValue("USERID");
            	var userid = Cookies.get("USERID");
            	$('#inputEmail').val(userid);
            	$('input:checkbox').prop('checked', true);
            	//$('input:checkbox').attr('checked', 'checked');
        	}

        	$('#btn').on('click',function(){
	        	if($('input:checkbox').prop('checked')== true){ // 체크박스 체크 시
		        	Cookies.set("REMEMBERME","Y"); // Y로 변경
		        	Cookies.set("USERID",$('#inputEmail').val()); // 입력한 아이디를 쿠키에 저장
	        	}else{
	        		Cookies.set("REMEMBERME","N"); // N로 변경
		        	Cookies.remove("USERID");
		       	}
	           	$('.form-signin').submit();
            })
        })
    </script>
  </head>

  <body>
    <div class="container">

      <form class="form-signin" action="${pageContext.request.contextPath}/login/process" method="POST">
        <h2 class="form-signin-heading">Please sign in</h2>
        <label for="inputEmail" class="sr-only">Email address</label>
        <input type="email" id="inputEmail" name = "userid" class="form-control" placeholder="Email address" required autofocus value = "brown">
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id="inputPassword" name = "pass" class="form-control" placeholder="Password" required value = "brownPass">
        <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me"> Remember me
          </label>
        </div>
        <button id="btn" class="btn btn-lg btn-primary btn-block" type="button">Sign in</button>
      </form>

    </div> <!-- /container -->

  </body>
</html>
    