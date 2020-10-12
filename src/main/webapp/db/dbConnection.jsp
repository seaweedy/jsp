<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	// DB 작업에 필요한 객체변수 선언
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null; // 쿼리문이 select인 경우에 사용함
			
			try {
				// 1. 드라이버 로딩
				// 클래스 메모리 로딩
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
				// 2. DB에 접속 ( 정상 접속이 된다면 Connection 객체 생성)
				String url = "jdbc:oracle:thin:@localhost:1521/xe";
				String id = "lmh";
				String pw = "java";
				
				// 주소와 id,pw를 이용하여 접속
				long startTime = System.currentTimeMillis(); // 시작하는 시간
				
				for(int i = 0; i < 20; i++){
					conn = DriverManager.getConnection(url, id, pw);
					conn.close();
				}
				
				long endTime = System.currentTimeMillis();
				
				out.print("<h3> endTime-startTime : " +(endTime-startTime) + " ms </h3>");
				
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				// 6. 종료(사용했던 자원을 모두 반납한다.)
				if(rs!=null)try {rs.close();} catch (SQLException e) {}
				if(stmt!=null)try {stmt.close();} catch (SQLException e) {}
				if(conn!=null)try {conn.close();} catch (SQLException e) {}
			}
	%>
</body>
</html>