<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 클라이언트의 모든 쿠키 가져오기
	Cookie[] cookies = request.getCookies();

	// 쿠키 순회
	for(int i = 0; i < cookies.length; i++) {
		out.println("<h3>쿠키이름 : " + cookies[i].getName() + "</h3>");
		out.println("<h3>쿠키값 : " + URLDecoder.decode(cookies[i].getValue(), "UTF-8") + "</h3>");
	}
	
	// 원하는 쿠키만 사용
	for(int i = 0; i < cookies.length; i++) {
		if(cookies[i].getName().equals("agree")) {
			out.println("<h3>쿠키이름 : " + cookies[i].getName() + "</h3>");
			out.println("<h3>쿠키값 : " + URLDecoder.decode(cookies[i].getValue(), "UTF-8") + "</h3>");
		}
	}
%>