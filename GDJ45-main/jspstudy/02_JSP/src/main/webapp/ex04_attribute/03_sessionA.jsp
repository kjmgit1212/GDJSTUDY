<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%-- session에 속성 name, age 저장하기 --%>
	<%
		session.setAttribute("name", "민경태");
		session.setAttribute("age", 45);
	%>
	
	<%-- redirect를 이용해서 03_sessionB.jsp로 이동하기 --%>
	<%
		response.sendRedirect("/JSP/ex04_attribute/03_sessionB.jsp");
	%>

</body>
</html>