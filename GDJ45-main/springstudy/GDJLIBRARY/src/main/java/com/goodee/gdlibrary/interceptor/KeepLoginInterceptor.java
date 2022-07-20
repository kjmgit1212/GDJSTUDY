package com.goodee.gdlibrary.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.WebUtils;

import com.goodee.gdlibrary.domain.MemberDTO;
import com.goodee.gdlibrary.service.MemberService;

public class KeepLoginInterceptor implements HandlerInterceptor {

	@Autowired
	private MemberService memberService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
	
		Cookie cookie = WebUtils.getCookie(request, "keepLogin");
		
		if(cookie != null) {
			
			MemberDTO loginMember = memberService.getMemberBySessionId(cookie.getValue());
			if (loginMember != null) {
				request.getSession().setAttribute("loginMember", loginMember);
			}
			
		}
		
		return true;
		
	}
	
}
