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
	
		// 서브밋
		$('#f').on('submit', function(event){
			if($('#title').val() == ''){
				alert("제목은 필수입니다.");
				$('#title').focus();
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

	<h3>작성화면</h3>
	
	<form id="f" action="${contextPath}/board/save" method="post">
		작성자 <input type="text" name="writer" value="${user.id}" readonly><br>
		제목 <input type="text" name="title" id="title"><br>
		내용<br>
		<textarea rows="3" cols="30" name="content"></textarea><br><br>
		<button>작성완료</button>
		<input type="button" value="목록" id="btnList">
	</form>

</body>
</html>