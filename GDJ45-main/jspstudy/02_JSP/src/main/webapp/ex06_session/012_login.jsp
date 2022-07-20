<%@page import="ex06_session.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");

	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	
	// id와 pw가 동일하면 로그인 성공으로 가정
	// 로그인 성공 : 로그인 한 사용자 정보를 session에 저장
	Member login = null;
	if(id.equals(pw)) {
		login = new Member();
		login.setId(id);
		login.setPw(pw);
		login.setName("민경태");
		session.setAttribute("login", login);
	}
	
	// 로그인 폼으로 돌아가기
	response.sendRedirect("/JSP/ex06_session/011_login_form.jsp");
	
%>