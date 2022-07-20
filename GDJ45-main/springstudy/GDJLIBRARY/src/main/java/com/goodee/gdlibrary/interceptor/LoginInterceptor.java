package com.goodee.gdlibrary.interceptor;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.goodee.gdlibrary.domain.DormantMemberDTO;
import com.goodee.gdlibrary.mapper.MemberMapper;
import com.goodee.gdlibrary.service.MemberService;
import com.goodee.gdlibrary.util.SecurityUtils;



public class LoginInterceptor implements HandlerInterceptor {

	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") != null) {
			session.removeAttribute("loginMember");
		}

		String memberId = SecurityUtils.xss(request.getParameter("memberId"));
		DormantMemberDTO member = memberMapper.selectDormantMemberById(memberId);
		
		if(member != null) {
			request.setAttribute("member", member);
			request.getRequestDispatcher("/member/dormantCancelPage").forward(request, response);
			return false;
		}
		return true;
		
	}
	

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		Map<String, Object> map = modelAndView.getModel();
		Object loginMember = map.get("loginMember");
		Object url = map.get("url");
		
		if(loginMember != null) {
			
			HttpSession session = request.getSession();
			session.setAttribute("loginMember", loginMember);
			
			String keepLogin = request.getParameter("keepLogin");
			if(keepLogin != null && keepLogin.equals("keep")) {
				
				Cookie cookie = new Cookie("keepLogin", session.getId()); 
				cookie.setPath("/");
				cookie.setMaxAge(60 * 60 * 24 * 7);  
				response.addCookie(cookie);
			}
			
			else {
				
				Cookie cookie = new Cookie("keepLogin", "");
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
			
			
			if(url.toString().isEmpty()) {  
				response.sendRedirect(request.getContextPath() + "/");
			} else {  
				response.sendRedirect(url.toString());
			}
			
		}
		
		else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			if(url.toString().isEmpty()) {
				out.println("<script>");
				out.println("alert('아이디 혹은 비밀번호가 맞지 않습니다.')");
				out.println("location.href='" + request.getContextPath() + "/member/loginPage'");
				out.println("</script>");
				out.close();
				//response.sendRedirect(request.getContextPath() + "/member/loginPage");				
			} else {
				out.println("<script>");
				out.println("alert('아이디 혹은 비밀번호가 맞지 않습니다.')");
				out.println("location.href='" + request.getContextPath() + "/member/loginPage?url=" + url.toString() + "'");
				out.println("</script>");
				out.close();
				//response.sendRedirect(request.getContextPath() + "/member/loginPage?url=" + url.toString());
			}
		}
		
	}
	
}