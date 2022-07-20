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
<script type="text/javascript">

	$(function(){
		
		// 삭제
		$('#btnRemove').on('click', function(){
			if(confirm('삭제할까요?')){
				location.href='${contextPath}/gallery/remove?galleryNo=${gallery.galleryNo}';
			}
		})
		
		// 수정페이지
		$('#btnChangePage').on('click', function(){
			location.href='${contextPath}/gallery/changePage?galleryNo=${gallery.galleryNo}';
		})
		
		// 목록
		$('#btnList').on('click', function(){
			location.href='${contextPath}/gallery/list';
		})
		
	})

</script>
</head>
<body>
	
	<h1>갤러리 상세 보기</h1>
	
	번호 ${gallery.galleryNo}<br>
	작성자 ${gallery.writer}<br>
	제목 ${gallery.title}<br>
	내용 ${gallery.content}<br>
	IP ${gallery.ip}<br>
	조회수 ${gallery.hit}<br>
	작성일 ${gallery.created}<br>
	수정일 ${gallery.modified}<br><br>
	
	<input type="button" value="삭제" id="btnRemove">
	<input type="button" value="수정페이지" id="btnChangePage">
	<input type="button" value="목록" id="btnList">
	
	<hr>
	
	<div>첨부목록</div>
	<c:forEach var="fileAttach" items="${fileAttaches}">
		<div><a href="${contextPath}/gallery/download?fileAttachNo=${fileAttach.fileAttachNo}">${fileAttach.origin}</a></div>
	</c:forEach>
	
	<hr>
	
	<c:forEach var="fileAttach" items="${fileAttaches}">
		<div><img alt="${fileAttach.origin}" src="${contextPath}/gallery/display?fileAttachNo=${fileAttach.fileAttachNo}" width="300px"></div>
	</c:forEach>
	
</body>
</html>