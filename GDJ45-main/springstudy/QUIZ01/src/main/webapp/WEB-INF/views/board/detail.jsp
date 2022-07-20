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
	$(document).ready(()=>{
		
		$('#f').on('submit', (event)=>{
			if($('#title').val() == ''){
				alert('제목은 필수입니다.');
				event.preventDefault();
			}
			if($('#title').val() == '${board.title}' && $('#content').val() == '${board.content}'){
				alert('수정된 내용이 없습니다.');
				event.preventDefault();
			}
		})
		
		$('#btnList').on('click', ()=>{
			location.href='${contextPath}/board/list';
		})
		
	})
</script>
</head>
<body>

	<h1>게시글수정화면</h1>
	
	<form id="f" action="${contextPath}/board/change" method="post">
		글번호 ${board.no}<br>
		작성자 ${board.writer}<br>
		작성IP ${board.ip}<br>
		조회수 ${board.hit}<br>
		작성일 ${board.created}<br>
		수정일 ${board.lastModified}<br>
		제목 <input type="text" name="title" id="title" value="${board.title}"><br>
		내용<br>
		<textarea name="content" id="content" rows="5" cols="30">${board.content}</textarea><br><br>
		<input type="hidden" name="no" value="${board.no}">
		<button>수정</button>
		<input type="button" value="목록" id="btnList">
	</form>
	
</body>
</html>