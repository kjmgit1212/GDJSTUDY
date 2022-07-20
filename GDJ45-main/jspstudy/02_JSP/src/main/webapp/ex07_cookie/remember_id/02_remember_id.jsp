<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");

	// 입력 안하면 빈 문자열("")
	String id = request.getParameter("id");

	// 체크 안하면 null
	String checkRememberId = request.getParameter("checkRememberId");
	
	/*
		아이디 기억하기
		1. 체크 했으면 아이디를 쿠키에 저장한다.
		2. 체크 안 했으면 아이디를 쿠키에서 삭제한다.
	*/
	if(checkRememberId != null) {
		Cookie cookie = new Cookie("rememberId", id);
		cookie.setMaxAge(60 * 60 * 24 * 15);
		response.addCookie(cookie);
	} else {
		Cookie cookie = new Cookie("rememberId", "");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}
	
	// 로그인 화면으로 돌아가기
	response.sendRedirect("01_login_form.jsp");
	
%>