<%@page import="ex06_session.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


	<%-- 로그인이 되었다면 session에 login 속성이 저장되어 있다. --%>
	<% Member login = (Member)session.getAttribute("login"); %>


	<%-- 로그인이 되었다면 로그인 사용자 정보와 로그아웃 버튼을 보여준다. --%>
	<% if (login != null) { %>
		${login.name}님 환영합니다.
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" value="로그아웃" onclick="location.href='/JSP/ex06_session/013_logout.jsp'">
	<% } else { %>
		<form action="/JSP/ex06_session/012_login.jsp" method="post">
			<input type="text" name="id" placeholder="아이디"><br>
			<input type="password" name="pw" placeholder="비밀번호"><br>
			<button>로그인</button>
		</form>
	<% } %>


</body>
</html>