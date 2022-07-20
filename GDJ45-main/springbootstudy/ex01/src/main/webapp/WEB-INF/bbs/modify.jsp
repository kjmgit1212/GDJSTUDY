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
			if( $('#title').val() == '' ){
				alert('제목은 필수입니다.');
				event.preventDefault();
				return false;
			}
			return true;
		})
	}
	
</script>
</head>
<body>

	<form id="f" action="${contextPath}/bbs/modify" method="post">
		<input type="hidden" name="bbsNo" value="${bbs.bbsNo}"><br>
		작성자 ${bbs.writer}<br>
		제목 <input type="text" name="title" id="title" value="${bbs.title}"><br>
		<textarea name="content" id="content">${bbs.content}</textarea><br><br>
		<button>수정완료</button>
		<input type="reset" value="다시작성">
		<input type="button" value="목록" onclick="location.href='${contextPath}/bbs/list'">
	</form>

</body>
</html>