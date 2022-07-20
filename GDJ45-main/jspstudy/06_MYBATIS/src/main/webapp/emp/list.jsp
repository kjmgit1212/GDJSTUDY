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

	<a href="/MYBATIS/insertPage.do">신규사원등록</a>

	<h1>전체사원목록</h1>
	<table border="1">
		<thead>
			<tr>
				<td>순번</td>
				<td>사원명</td>
				<td>입사일자</td>			
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty list}">
				<tr>
					<td colspan="3">사원 없음</td>
				</tr>
			</c:if>
			<c:if test="${not empty list}">
				<c:forEach items="${list}" var="emp" varStatus="v">
					<tr>
						<td>${v.count}</td>
						<td><a href="/MYBATIS/detail.do?no=${emp.no}">${emp.name}</a></td>
						<td>${emp.hired}</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
</body>
</html>