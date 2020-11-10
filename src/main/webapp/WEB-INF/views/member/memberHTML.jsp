<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="form-group">
	<label for="userNm" class="col-sm-2 control-label">사용자 사진</label>
	<div class="col-sm-10">
		<%-- 							<img alt="" src="${cp }/profile/${memberVo.filename}"/> --%>

		<img alt="" src="${cp }/profileImgView?userid=${memberVo.userid }" /><br>
		<button id="profileDownBtn" type="button" class="btn btn-default">다운로드
			: ${memberVo.realfilename }</button>
	</div>
</div>

<div class="form-group">
	<label for="userNm" class="col-sm-2 control-label">사용자 아이디</label>
	<div class="col-sm-10">
		<label class="control-label">${memberVo.userid }</label>
	</div>
</div>

<div class="form-group">
	<label for="userNm" class="col-sm-2 control-label">사용자 이름</label>
	<div class="col-sm-10">
		<label class="control-label">${memberVo.usernm }</label>
	</div>
</div>
<div class="form-group">
	<label for="userNm" class="col-sm-2 control-label">별명</label>
	<div class="col-sm-10">
		<label class="control-label">${memberVo.alias }</label>
	</div>
</div>
<div class="form-group">
	<label for="pass" class="col-sm-2 control-label">Password</label>
	<div class="col-sm-10">
		<label class="control-label">********</label>
	</div>
</div>

<div class="form-group">
	<label for="addr1" class="col-sm-2 control-label">주소</label>
	<div class="col-sm-10">
		<label class="control-label">${memberVo.addr1 }</label>
	</div>
</div>

<div class="form-group">
	<label for="addr2" class="col-sm-2 control-label">상세주소</label>
	<div class="col-sm-10">
		<label class="control-label">${memberVo.addr2 }</label>
	</div>
</div>

<div class="form-group">
	<label for="zipcode" class="col-sm-2 control-label">우편번호</label>
	<div class="col-sm-10">
		<label class="control-label">${memberVo.zipcode }</label>
	</div>
</div>

<div class="form-group">
	<label for="reg_dt" class="col-sm-2 control-label">등록일자</label>
	<div class="col-sm-10">
		<label class="control-label"><fmt:formatDate value="${memberVo.reg_dt }" pattern="yyyy-MM-dd" /></label>
	</div>
</div>

<div class="form-group">
	<div class="col-sm-offset-2 col-sm-10">
		<a class="btn btn-default pull-right"
			href="${cp }/member/updateForm?userid=${memberVo.userid }">사용자
			수정</a>
	</div>
</div>
