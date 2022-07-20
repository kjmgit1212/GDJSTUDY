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
	
		// 서브밋(수정)
		$('#f').on('submit', function(event){
			if($('#title').val() == ''){
				alert('제목은 필수입니다.');
				event.preventDefault();
				return;
			} else if($('#title').val() == '${board.title}' && $('#content').val() == '${board.content}'){
				alert('수정할 내용이 없습니다.');
				event.preventDefault();
				return;
			}
		})
		
		// 목록
		$('#btnList').on('click', function(){
			location.href='${contextPath}/board/list';
		})
		
	})
	
</script>
</head>
<body>

	<h3>수정화면</h3>

	<form id="f" action="${contextPath}/board/change" method="post">
		제목 <input type="text" name="title" id="title" value="${board.title}"><br>
		내용<br>
		<textarea rows="5" cols="30" name="content" id="content">${board.content}</textarea><br><br>
		<input type="hidden" name="boardNo" value="${board.boardNo}">
		<button>수정완료</button>
		<input type="button" value="목록" id="btnList">
	</form>

</body>
</html>