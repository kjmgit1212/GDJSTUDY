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

	<a href="${contextPath}/bbs/addPage">작성하기</a>

	<table border="1">
		<thead>
			<tr>
				<td>제목</td>
				<td>작성자</td>
				<td>작성일</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${bbses}" var="bbs">
				<tr>
					<td><a href="${contextPath}/bbs/detail?bbsNo=${bbs.bbsNo}">${bbs.title}</a></td>
					<td>${bbs.writer}</td>
					<td>${bbs.created}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>