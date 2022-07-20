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

	<form action="${contextPath}/notice/save" method="post">
	
		<input type="text" name="title" id="title" placeholder="제목" required><br>
		<textarea rows="5" cols="30" name="content" id="content"></textarea><br><br>
		
		<button>등록</button>
	
	</form>
	
	
	
	
	

</body>
</html>