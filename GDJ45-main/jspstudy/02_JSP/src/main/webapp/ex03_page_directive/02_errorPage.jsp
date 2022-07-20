<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- 현재 페이지에서 오류가 발생하면 error.jsp로 이동하시오. --%>
<%@ page errorPage="error.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>안녕하세요. 반갑습니다.</h1>
	
	<%
		Integer.parseInt("1.5");  // NumberFormatException 발생하면서 error.jsp로 이동
	%>

</body>
</html>