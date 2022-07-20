<%@page import="java.util.ArrayList"%>
<%@page import="domain.Board"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%-- 1 ~ 5 --%>
	<c:forEach var="n" begin="1" end="5" step="1">
		${n}&nbsp;	
	</c:forEach>
	
	<hr>
	
	<%-- 5 ~ 1 --%>
	<c:forEach var="k" begin="1" end="5" step="1">
		${(5 + 1) - k}&nbsp;
	</c:forEach>
	
	<hr>
	
	<%-- 1월 ~ 12월 --%>
	<select name="month">
		<c:forEach var="m" begin="1" end="12" step="1">
			<option value="${m}">${m}월</option>
		</c:forEach>
	</select>
	
	<hr>
	
	<%-- 배열 --%>
	<%
		String[] seasons = {"spring", "summer", "autumn", "winter"};
		pageContext.setAttribute("seasons", seasons);  // EL 사용을 위해서
	%>
	
	<c:forEach items="${seasons}" var="season" varStatus="v">
		인덱스: ${v.index}, 요소: ${season}<br>
	</c:forEach>
	
	<hr>
	
	<%-- List --%>
	<%
		List<String> hobbies = Arrays.asList("여행", "운동", "넷플릭스");
		pageContext.setAttribute("hobbies", hobbies);
	%>
	<c:forEach items="${hobbies}" var="hobby" varStatus="v">
		인덱스: ${v.index}, 요소: ${hobby}<br>
	</c:forEach>
	
	<hr>
	
	<%-- 객체(인스턴스) --%>
	<%
		Board board = new Board("공지", "공지한다", "관리자", 50);
		pageContext.setAttribute("board", board);
	%>
	${board.title}<br>
	${board.content}<br>
	${board.writer}<br>
	${board.hit}<br>
	
	<hr>
	
	<%-- 연습 --%>
	<%
		List<Board> boards = new ArrayList<>();
		boards.add(new Board("필독", "중요함", "관리자", 100));
		boards.add(new Board("요청", "요청함", "사용자", 10));
		boards.add(new Board("공구", "공구함", "운영자", 200));
		pageContext.setAttribute("boards", boards);
	%>
	<table border="1">
		<thead>
			<tr>
				<td>번호</td>
				<td>제목</td>
				<td>내용</td>
				<td>작성자</td>
				<td>조회수</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${boards}" var="board" varStatus="v">
				<tr>
					<td>${v.index + 1}</td>
					<td>${board.title}</td>
					<td>${board.content}</td>
					<td>${board.writer}</td>
					<td>${board.hit}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>