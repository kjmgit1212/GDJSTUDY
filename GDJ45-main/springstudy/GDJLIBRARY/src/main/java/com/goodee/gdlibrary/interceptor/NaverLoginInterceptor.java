package com.goodee.gdlibrary.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class NaverLoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession session = request.getSession();
		if (session.getAttribute("loginMember") != null) {
			session.removeAttribute("loginMember");
		}

		return true;

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		Map<String, Object> map = modelAndView.getModel();
		Object loginMember = map.get("loginMember");

		if (loginMember != null) {

			HttpSession session = request.getSession();
			session.setAttribute("loginMember", loginMember);

			response.sendRedirect(request.getContextPath() + "/");

		}

		else {

			response.sendRedirect(request.getContextPath() + "/member/loginPage");

		}

	}

}
