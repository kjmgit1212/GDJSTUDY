package com.goodee.gdlibrary.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.goodee.gdlibrary.domain.MemberDTO;

public class AdminInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		MemberDTO member = (MemberDTO)request.getSession().getAttribute("loginMember");
		PrintWriter out = response.getWriter();
		System.out.println();
		if(request.getSession().getAttribute("loginMember") == null) {
			response.setContentType("text/html");
			out = response.getWriter();
			out.println("<script>");
			out.println("if(confirm('로그인이 필요한 기능입니다. 로그인 할까요?')){");
			out.println("location.href='" + request.getContextPath() + "/member/loginPage?url=" + request.getRequestURL() + "'");
			out.println("}else{");
			out.println("history.back()");
			out.println("}");
			out.println("</script>");
			out.close();
			return false;
		} else if(!member.getMemberId().equals("admin")) {
			response.setContentType("text/html");
			out.println("<script>");
			out.println("alert('관리자 로그인이 필요한 기능입니다.')");
			out.println("location.href='" + request.getContextPath() + "/'");
			out.println("</script>");
			out.close();
			
		}
		return true;
		
	}
}
