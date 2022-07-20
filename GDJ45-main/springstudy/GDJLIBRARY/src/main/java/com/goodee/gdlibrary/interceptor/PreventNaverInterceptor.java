package com.goodee.gdlibrary.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import com.goodee.gdlibrary.domain.MemberDTO;
import com.goodee.gdlibrary.mapper.MemberMapper;

public class PreventNaverInterceptor implements HandlerInterceptor {

	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		MemberDTO loginMember = (MemberDTO) request.getSession().getAttribute("loginMember");

		if(loginMember != null) {
			MemberDTO member = memberMapper.selectNaverLoginCheck(loginMember.getMemberId());
			
			if(member != null) {
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('네이버 아이디로 로그인 한 회원은 해당 기능은 사용할 수 없습니다.')");
				out.println("location.href='" + request.getContextPath() + "/member/myPage'");
				out.println("</script>");
				out.close();
				return false;
			}
		}
		
		return true;
		
	}
	
}
