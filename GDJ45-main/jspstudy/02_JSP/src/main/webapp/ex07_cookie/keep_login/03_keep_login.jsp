<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 로그인 유지 상태라면 쿠키에 로그인 정보가 있다.
	// 쿠키에 있는 로그인 정보를 세션으로 옮긴다.
	
	// 로그인 유지 상태가 아니라면 세션에 로그인 정보가 있다.
	// 추가로 할 일이 없다.
	
	Cookie[] cookies = request.getCookies();
	for(int i = 0; i < cookies.length; i++) {
		if(cookies[i].getName().equals("login_id")) {
			session.setAttribute("login_id", cookies[i].getValue());
		} else if(cookies[i].getName().equals("login_name")) {
			session.setAttribute("login_name", cookies[i].getValue());
		}
	}
%>

<%-- session에 저장된 로그인 정보를 이용해서 로그인 화면 만들기 --%>
<div>
	${login_id}님 환영합니다.
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="04_logout.jsp">로그아웃</a>
</div>