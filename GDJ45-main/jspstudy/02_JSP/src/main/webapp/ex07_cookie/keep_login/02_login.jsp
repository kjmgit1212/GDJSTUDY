<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");

	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	
	// 아이디==비밀번호이면 로그인 성공으로 가정
	if(id.equals(pw)) {
		
		// 로그인 유지 체크 여부 확인
		String checkKeepLogin = request.getParameter("checkKeepLogin");
		
		// 체크했으면 로그인 정보를 쿠키에 저장(브라우저를 닫은 뒤에도 정보를 유지하기 위함)
		// 체크 안 했으면 로그인 정보를 세션에 저장
		if(checkKeepLogin != null) {
			Cookie cookie1 = new Cookie("login_id", id);
			cookie1.setMaxAge(60 * 60 * 24);
			response.addCookie(cookie1);
			Cookie cookie2 = new Cookie("login_name", "민경태");
			cookie2.setMaxAge(60 * 60 * 24);
			response.addCookie(cookie2);
		} else {
			session.setAttribute("login_id", id);
			session.setAttribute("login_name", "민경태");
		}
		
	}
	
	response.sendRedirect("03_keep_login.jsp");
	
%>