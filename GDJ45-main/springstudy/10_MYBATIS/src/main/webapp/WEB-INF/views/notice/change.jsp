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

	<form action="${contextPath}/notice/change" method="post">
	
		<input type="text" name="title" id="title" value="${notice.title}" required><br>
		<textarea rows="5" cols="30" name="content" id="content">${notice.content}</textarea><br><br>
		<input type="hidden" name="noticeNo" value="${notice.noticeNo}">
		
		<button>수정완료</button>
	
	</form>
	
</body>
</html>