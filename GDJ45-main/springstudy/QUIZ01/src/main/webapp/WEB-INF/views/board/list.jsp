<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<style>
	.fa-trash-can:hover {
		cursor: pointer;
	}
</style>
<script src="../resources/js/jquery-3.6.0.js"></script>
<script>
	$(document).ready(function(){
		$('.fa-trash-can').on('click', function(){
			if(confirm('삭제할까요?')){
				alert($(this).data('no'));
				location.href='${contextPath}/board/remove?no=' + $(this).data('no');
			}
		})
	})
</script>
</head>
<body>
	
	<h1>게시글목록화면</h1>

	<a href="${contextPath}/board/savePage">새글작성</a>
	
	<hr>

	<table border="1">
		<caption>전체게시글 ${count}개</caption>
		<thead>
			<tr>
				<td>게시글번호</td>
				<td>제목</td>
				<td>작성자</td>
				<td>조회수</td>
				<td>작성일자</td>
				<td>삭제</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${boards}" var="board">
				<tr>
					<td>${board.no}</td>
					<td><a href="${contextPath}/board/detail?no=${board.no}">${board.title}</a></td>
					<td>${board.writer}</td>
					<td>${board.hit}</td>
					<td>${board.created}</td>
					<td><i class="fa-solid fa-trash-can" data-no="${board.no}"></i></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>