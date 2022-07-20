<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	/*
		쿠키 삭제하기
		1. 삭제할 쿠키와 같은 이름의 쿠키를 만든다.
		2. 쿠키 유효시간을 0으로 설정한다.
		3. 쿠키를 저장한다.(덮어쓰기한다.)
	*/
	
	// agree 쿠키와 address 쿠키를 삭제하시오.
	Cookie cookie1 = new Cookie("agree", "");
	cookie1.setMaxAge(0);
	response.addCookie(cookie1);
	
	Cookie cookie2 = new Cookie("address", "");
	cookie2.setMaxAge(0);
	response.addCookie(cookie2);
%>