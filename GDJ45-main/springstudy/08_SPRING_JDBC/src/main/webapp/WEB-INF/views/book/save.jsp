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
	$(document).ready(function(){
		
		$('#f').on('submit', function(event){
			
			if($('#title').val() == '' || $('#author').val() == ''){
				alert('제목과 저자는 필수입니다.');
				event.preventDefault();  // return;
			}
			
			var regPubDate = /^[0-9]{4}-[0-9]{2}-[0-9]{2}$/;
			if( regPubDate.test($('#pubDate').val()) == false ) {
				alert('출판일은 YYYY-MM-DD 형식으로 입력해야 합니다.');
				event.preventDefault();
			}
			
		})  // submit
		
		$('#btnList').on('click', function(event){
			location.href='${contextPath}/book/list';
		})
		
	})
</script>
</head>
<body>

	<form id="f" action="${contextPath}/book/save" method="post">
		<input type="text" name="title" id="title" placeholder="제목"/><br>
		<input type="text" name="author" id="author" placeholder="저자"/><br>
		<input type="text" name="price" id="price" placeholder="가격"/><br>
		<input type="text" name="pubDate" id="pubDate" placeholder="출판일(YYYY-MM-DD)"/><br>
		<button>등록</button>
		<input type="reset" value="다시작성">
		<input type="button" value="목록" id="btnList">
	</form>

</body>
</html>