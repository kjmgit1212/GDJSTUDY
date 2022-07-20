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

	<%-- choose 태그는 if - else if - else 구조를 가진다. --%>
	
	<c:set var="score" value="70" />
	
	<c:choose>
		<c:when test="${score >= 90}">A학점</c:when>
		<c:when test="${score >= 80}">B학점</c:when>
		<c:when test="${score >= 70}">C학점</c:when>
		<c:when test="${score >= 60}">D학점</c:when>
		<c:otherwise>F학점</c:otherwise>
	</c:choose>
	
</body>
</html>