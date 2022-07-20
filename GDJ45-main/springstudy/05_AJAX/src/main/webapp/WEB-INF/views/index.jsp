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

	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	
	<a href="${contextPath}/member">회원관리</a>
	<br>
	
	<a href="${contextPath}/board">게시판관리</a>
	<br>
	
	<a href="${contextPath}/product">제품관리</a>
	<br>
	
	<a href="${contextPath}/reservation">예약관리</a>
	<br>
	
	<a href="${contextPath}/openapi">오픈API</a>

</body>
</html>