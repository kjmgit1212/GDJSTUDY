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
			if($('#writer').val() == '' || $('#title').val() == ''){
				alert('작성자와 제목은 필수입니다.');
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

	<h1>게시글작성화면</h1>
	
	<form id="f" action="${contextPath}/board/save" method="post">
		작성자 <input type="text" name="writer" id="writer"><br>
		제목 <input type="text" name="title" id="title"><br>
		내용<br>
		<textarea name="content" id="content" rows="5" cols="30"></textarea><br><br>
		<button>작성</button>
		<input type="reset" value="다시작성">
		<input type="button" value="목록" id="btnList">
	</form>

</body>
</html>