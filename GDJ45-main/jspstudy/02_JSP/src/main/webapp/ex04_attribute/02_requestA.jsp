<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%-- request에 속성 name, age 저장하기 --%>
	<%
		request.setAttribute("name", "민경태");
		request.setAttribute("age", 45);
	%>
	
	<%-- request 전달을 위한 forward 방법 1 --%>
	<%
		// request.getRequestDispatcher("02_requestB.jsp").forward(request, response);
	%>
	
	<%-- request 전달을 위한 forward 방법 2 --%>
	<jsp:forward page="02_requestB.jsp"></jsp:forward>

</body>
</html>