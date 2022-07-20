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
<script src="../resources/summernote-0.8.18-dist/summernote-lite.js"></script>
<script src="../resources/summernote-0.8.18-dist/lang/summernote-ko-KR.js"></script>
<link rel="stylesheet" href="../resources/summernote-0.8.18-dist/summernote-lite.css"/>
<script src="../resources/js/summernote.js"></script>
<script>

	$(function(){
		fnSubmit();
		fnSummernote();  // summernote.js에 포함되어 있는 함수
	})
	
	function fnSubmit(){
		$('#f').on('submit', function(event){
			if( $('#writer').val() == '' || $('#title').val() == '' ){
				alert('작성자와 제목은 필수입니다.');
				event.preventDefault();
				return false;
			}
			return true;
		})
	}

</script>
</head>
<body>

	<h1>게시글작성화면</h1>
	
	<form id="f" action="${contextPath}/bbs/add" method="post">
		<input type="text" name="writer" placeholder="작성자"><br>
		<input type="text" name="title" placeholder="제목"><br>
		<textarea name="content" id="content"></textarea><br><br>
		<button>작성완료</button>
	</form>

</body>
</html>