<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 로그아웃 : session에 저장된 모든 정보를 초기화
	session.invalidate();

	// 로그인 화면으로 돌아가기
	response.sendRedirect("/JSP/ex06_session/011_login_form.jsp");
%>