<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<a href="${contextPath}/board/addPage">작성하기</a>

	<table border="1">
		<thead>
			<tr>
				<td>제목</td>
				<td>작성자</td>
				<td>최종수정일</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${boards}" var="board">
				<tr>
					<td><a href="${contextPath}/board/detail?board_no=${board.board_no}">${board.title}</a></td>
					<td>${board.writer}</td>
					<td>${board.lastModified}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>