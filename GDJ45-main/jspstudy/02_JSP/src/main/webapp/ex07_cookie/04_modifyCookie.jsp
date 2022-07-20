<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	/*
		쿠키 변경하기
		1. 모든 쿠키를 읽는다.
		2. 변경할 쿠키를 찾는다.
		3. 변경할 쿠키와 같은 이름을 가진 새로운 쿠키를 만든다.
		4. 새로운 쿠키를 저장한다.(덮어쓰기한다.)
	*/
	
	// agree 쿠키값을 no로 수정하시오.
	// 유효시간을 10분으로 수정하시오.
	
	Cookie[] cookies = request.getCookies();

	for(int i = 0; i < cookies.length; i++) {
		if(cookies[i].getName().equals("agree")) {
			Cookie cookie = new Cookie("agree", "no");
			cookie.setMaxAge(60 * 10);
			response.addCookie(cookie);
			break;
		}
	}
	
%>