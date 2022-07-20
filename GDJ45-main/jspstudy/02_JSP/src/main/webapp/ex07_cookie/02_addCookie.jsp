<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	// 쿠키이름 : 알파벳과 숫자로 구성
	// 쿠키값   : 공백, 콜론, 세미콜론, 등호 등 불가
	//            인코딩을 통해서 쿠키값으로 포함시킬 수 있음

	// 쿠키 생성
	Cookie cookie = new Cookie("address", URLEncoder.encode("서울시 영등포구 여의도동", "UTF-8"));

	// 쿠키 유효시간 정하기
	cookie.setMaxAge(60 * 60 * 24 * 15);
	
	// 쿠키 저장
	response.addCookie(cookie);
%>

<%-- 인코딩 된 쿠키값은 디코딩해서 확인해야 한다. --%>
<h3>쿠키이름 : <%=cookie.getName()%></h3>
<h3>쿠키값 : <%=URLDecoder.decode(cookie.getValue(), "UTF-8")%></h3>
