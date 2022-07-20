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

	<%--
		age는 문자열 "45"를 저장한다.
		request.setAttribute("age", "45");	
	--%>
	<c:set var="age" value="45" scope="request" />
	
	<%-- request를 전달하는 forward --%>
	<% request.getRequestDispatcher("022_set.jsp").forward(request, response); %>

</body>
</html>