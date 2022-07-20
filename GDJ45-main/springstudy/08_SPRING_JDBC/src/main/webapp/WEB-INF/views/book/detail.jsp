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
			
			// 기존 책 정보         사용자가 입력한 책 정보
			// ${book.title}        $('#title').val()
			// ${book.author}       $('#author').val()
			// ${book.price}        $('#price').val()
			
			if('${book.title}' == $('#title').val() && '${book.author}' == $('#author').val() && '${book.price}' == $('#price').val()){
				alert('변경된 정보가 없습니다.');
				event.preventDefault();
			}
			
		})  // submit
		
		$('#btnRemove').on('click', function(event){
			if(confirm('"${book.title}" 책을 삭제할까요?')){
				location.href='${contextPath}/book/remove?book_no=' + ${book.book_no};
			}
		})
		
		$('#btnList').on('click', function(event){
			location.href='${contextPath}/book/list';
		})
		
	})
</script>
</head>
<body>

	<form id="f" action="${contextPath}/book/change" method="post">
		<input type="text" name="title" id="title" value="${book.title}"><br>
		<input type="text" name="author" id="author" value="${book.author}"><br>
		<input type="text" name="price" id="price" value="${book.price}"><br>
		출판일 ${book.pubDate}<br>
		등록일 ${book.regDate}<br>
		<input type="hidden" name="book_no" value="${book.book_no}">
		<button>수정</button>
		<input type="button" value="삭제" id="btnRemove">
		<input type="reset" value="다시작성">
		<input type="button" value="목록" id="btnList">
	</form>

</body>
</html>