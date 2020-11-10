<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script>
	$(document).ready(function(){
		// client side에서는 서버사이드변수나 값을 사용가능
		console.log("${memberVo.userid}");
		
		memberAjax("${memberVo.userid}");
		
	});
	function memberAjax(userid){
		$.ajax(
				{url: "/member/getHTML",
				data : {userid : userid},
				method : "post",
				success : function(data){

					$("#frm").html(data);

		$("#profileDownBtn").on("click",function(){
			console.log("버튼클릭")
			document.location="/profileDownloadView?userid=${memberVo.userid}";
		});
			}
		});
	}
</script>
<div class="container-fluid">
	<div class="row">
		<div class="col-sm-8 blog-main">
			<form id="frm" class="form-horizontal" role="form">
				
			</form>
		</div>
	</div>
</div>
</html>