<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 세션 초기화
	session.invalidate();
	
	// 쿠키 삭제
	Cookie cookie1 = new Cookie("login_id", "");
	cookie1.setMaxAge(0);
	response.addCookie(cookie1);
	Cookie cookie2 = new Cookie("login_name", "");
	cookie2.setMaxAge(0);
	response.addCookie(cookie2);
	
	// 이동
	response.sendRedirect("01_login_form.jsp");
%>