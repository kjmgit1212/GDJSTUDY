<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../resources/js/jquery-3.6.0.js"></script>
<script>
	
	$(function(){
	
		// 삭제
		$('#btnRemove').on('click', function(){
			if(confirm('삭제할까요?')){
				location.href='${contextPath}/bbs/remove?bbsNo=${bbs.bbsNo}';
			}
		})
		
		// 수정 화면으로 이동
		$('#btnModifyPage').on('click', function(){
			location.href='${contextPath}/bbs/modifyPage?bbsNo=${bbs.bbsNo}';
		})
		
		// 목록
		$('#btnList').on('click', function(){
			location.href='${contextPath}/bbs/list';
		})
		
	})
	
</script>
</head>
<body>

	게시글번호: ${bbs.bbsNo}<br>
	작성자: ${bbs.writer}<br>
	최초작성일: ${bbs.created}<br>
	최종수정일: ${bbs.modified}<br>
	제목: ${bbs.title}<br>
	내용
	${bbs.content}<br>
	
	<hr>
	
	<input type="button" value="삭제" id="btnRemove">
	<input type="button" value="수정" id="btnModifyPage">
	<input type="button" value="목록" id="btnList">
	
</body>
</html>