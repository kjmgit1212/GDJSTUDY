<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%-- application에 속성 name, age 저장하기 --%>
	<%
		application.setAttribute("name", "민경태");
		application.setAttribute("age", 45);
	%>
	
	<a href="/JSP/ex04_attribute/04_applicationB.jsp">이동</a>

</body>
</html>