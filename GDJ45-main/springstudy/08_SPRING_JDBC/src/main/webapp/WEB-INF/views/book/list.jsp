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
		
		// 제목을 클릭하면 상세보기로 이동
		/*
		$('.book_title').on('click', function(){
			location.href='${contextPath}/book/detail?book_no=' + $(this).data('book_no');  // data-book_no 속성 값
			location.href='${contextPath}/book/detail?book_no=' + $(this).prev().text();    // <td>${book.book_no}</td>                                                                                // <td class="book_title">
		})
		*/
		
		// 클릭 대상이 행 전체로 작성된 상세보기 이동
		// <tr data-book_no="${book.book_no}">
		// $('tbody tr') : <tbody> 태그 아래에 있는 <tr> 태그
		$('tbody tr').on('click', function(){
			location.href='${contextPath}/book/detail?book_no=' + $(this).data('book_no');
		})
		
		$('#btnSave').on('click', function(){
			location.href='${contextPath}/book/savePage';
		})
		
	})
</script>
<style>
	tbody tr:hover {
		background-color: teal;
	}
</style>
</head>
<body>

	<input type="button" value="등록" id="btnSave">
	
	<hr>
	
	<table border="1">
		<thead>
			<tr>
				<td>번호</td>
				<td>제목</td>
				<td>저자</td>
				<td>출판일</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="book" items="${books}">
				<tr data-book_no="${book.book_no}">
					<td>${book.book_no}</td>
					<td class="book_title" data-book_no="${book.book_no}">${book.title}</td>
					<td>${book.author}</td>
					<td>${book.pubDate}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>