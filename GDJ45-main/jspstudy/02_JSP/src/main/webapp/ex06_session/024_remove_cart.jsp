<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// session에 저장된 장바구니 지우기
	session.removeAttribute("cart");

	response.sendRedirect("/JSP/ex06_session/023_cart.jsp");
%>