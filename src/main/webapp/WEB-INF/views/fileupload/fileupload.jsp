<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- client form method : post
					enctype : multipart/form-data
		server - servlet @MultipartConfig
			   - spring Framework MultipartResolver
	 -->
	<form action="${cp}/fileupload/upload" method="post" enctype="multipart/form-data">
		<label>아이디 : </label><input type="text" name="userid" value="브라운"><br>
		<label>파일업로드 : </label><input type="file" name="file"><br>
		<input type="submit" value="전송"><br>
	</form>
</body>
</html>