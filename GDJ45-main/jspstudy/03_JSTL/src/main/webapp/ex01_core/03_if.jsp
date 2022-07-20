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

	<%-- if 태그는 else, else if가 없다. --%>

	<c:set var="score" value="99" />
	
	<c:if test="${score >= 90}">
		A학점
	</c:if>
	<c:if test="${score < 90 and score >= 80}">
		B학점
	</c:if>
	<c:if test="${score < 80 and score >= 70}">
		C학점
	</c:if>
	<c:if test="${score < 70 and score >= 60}">
		D학점
	</c:if>
	<c:if test="${score < 60}">
		F학점
	</c:if>

</body>
</html>