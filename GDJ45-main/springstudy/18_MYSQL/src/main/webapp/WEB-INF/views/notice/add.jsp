<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath}" var="contextPath" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../resources/js/jquery-3.6.0.js"></script>
<script>
	$(function(){
		fnAdd();
		fnList();
	})
	function fnAdd(){
		$('#f').on('submit', function(event){
			if($('#title').val() == ''){
				alert('제목 필수');
				event.preventDefault();
				return false;
			}
			return true
		})
	}
	function fnList(){
		$('#btnList').on('click', function(){
			location.href='${contextPath}/notice/list';
		})
	}
</script>
</head>
<body>

	<h1>공지사항 작성 화면</h1>
	<form id="f" method="post" action="${contextPath}/notice/add">
		작성자
		<select name="memberNo">
			<option value="1">제임스</option>
			<option value="2">에밀리</option>
			<option value="3">크리스틴</option>
		</select><br>
		<input type="text" name="title" id="title" placeholder="제목"><br>
		<textarea rows="2" cols="25" name="content" id="content" placeholder="내용"></textarea><br><br>
		<button>작성완료</button>
		<input type="button" value="목록" id="btnList">
	</form>

</body>
</html>