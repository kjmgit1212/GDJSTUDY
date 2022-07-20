<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 1. 쿠키 생성
	Cookie cookie = new Cookie("agree", "yes");

	// 2. 쿠키 유효시간 정하기
	cookie.setMaxAge(60 * 60 * 24 * 15);  // 초 단위(60초 * 60분 * 24시간 * 15일)
	
	// 3. 쿠키 저장
	// 클라이언트의 웹 브라우저에 저장하기 위해서
	// 서버가 클라이언트로 데이터를 보내는 "응답(response)"으로 처리
	response.addCookie(cookie);
%>

<%-- 쿠키 확인 : F12 - Application - 좌측 Storage - Cookies --%>