package com.goodee.ex15.controller;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goodee.ex15.domain.MemberDTO;
import com.goodee.ex15.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/member/agreePage")
	public String agreePage() {
		return "member/agree";
	}
	
	@GetMapping("/member/signInPage")
	public String signInPage(@RequestParam(required=false) String[] agreements, Model model) {
		model.addAttribute("agreements", agreements);
		return "member/signIn";
	}
	
	@ResponseBody
	@GetMapping(value="/member/idCheck", produces="application/json")
	public Map<String, Object> idCheck(@RequestParam String id) {
		return memberService.idCheck(id);
		// {"res": null}
		// {"res": {"memberNo":1, ...}}
	}
	
	@ResponseBody
	@GetMapping(value="/member/emailCheck", produces="application/json")
	public Map<String, Object> emailCheck(@RequestParam String email) {
		return memberService.emailCheck(email);
		// {"res": null}
		// {"res": {"memberNo":1, ...}}
	}
	
	@ResponseBody
	@GetMapping(value="/member/sendAuthCode", produces="application/json")
	public Map<String, Object> sendAuthCode(@RequestParam String email) {
		return memberService.sendAuthCode(email);
	}
	
	@PostMapping("/member/signIn")
	public void signIn(HttpServletRequest request, HttpServletResponse response) {
		memberService.signIn(request, response);
	}
	
	@GetMapping("/member/signOut")
	public void signOut(HttpServletRequest request, HttpServletResponse response) {
		memberService.signOut(request, response);
	}
	
	@GetMapping("/member/loginPage")
	public String loginPage(@RequestParam(required=false) String url, Model model) {
		model.addAttribute("url", url);  // member/login.jsp로 url 속성값을 전달한다. 
		return "member/login";
	}
	
	// login() 메소드 수행 전에 LoginInterceptor의 preHandle() 메소드가 호출
	@PostMapping("/member/login")
	public void login(HttpServletRequest request, Model model) {
		
		// 아이디, 비밀번호가 일치하는 회원 정보 가져오기
		MemberDTO loginMember = memberService.login(request);
		
		// 아이디, 비밀번호가 일치하는 회원이 있으면(로그인 성공) LoginIntercepter의 postHandle() 메소드에 회원 정보 전달
		if(loginMember != null) {
			model.addAttribute("loginMember", loginMember);     // Model에 저장된 속성은 LoginInterceptor의 postHandle() 메소드의 ModelAndView 매개변수가 받는다.
		}
		
		// LoginIntercepter의 postHandle() 메소드에 로그인 이후에 이동할 경로 전달
		model.addAttribute("url", request.getParameter("url"));
		
		// 로그인 유지를 체크한 경우
		// 1) check 상태   : 파라미터 keepLogin = "keep"
		// 2) uncheck 상태 : 파라미터 keepLogin 자체가 없다. 따라서 null.
		String keepLogin = request.getParameter("keepLogin");
		if(keepLogin != null && keepLogin.equals("keep")) {
			memberService.keepLogin(request);
		}
		
	}
	// login() 메소드 수행 후에 LoginInterceptor의 postHandle() 메소드가 호출
	
	// LoginInterceptor의 preHandle() 메소드에서 탈퇴자 조회 후 탈퇴자인 경우 처리
	@PostMapping("/member/reSignInPage")
	public String reSignInPage() {
		return "member/reSignIn";
	}
	
	@PostMapping("/member/reSignIn")
	public void reSignIn(HttpServletRequest request, HttpServletResponse response) {
		memberService.reSignIn(request, response);
	}
	
	// 실제 구현에서는 BoardController에 있음
	@GetMapping("/board/savePage")
	public String savePage() {
		return "board/save";
	}
	
	@GetMapping("/member/logout")
	public String logout(HttpSession session, HttpServletResponse response) {
		// session의 모든 정보(로그인 정보 포함) 제거
		MemberDTO loginMember = (MemberDTO)session.getAttribute("loginMember");
		if(loginMember != null) {
			session.invalidate();
		}
		// 로그인 유지를 체크한 사용자의 경우 keepLogin 쿠키 제거
		Cookie cookie = new Cookie("keepLogin", "");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		return "redirect:/";  // contextPath 이동
	}
	
	/* 아이디 찾기 */
	@GetMapping("/member/findIdPage")
	public String findIdPage() {
		return "member/findId";
	}
	
	@ResponseBody
	@PostMapping(value="/member/findId", produces="application/json")
	public Map<String, Object> findId(@RequestBody MemberDTO member) {
		return memberService.findId(member);
	}
	
	/* 비밀번호 찾기 */
	@GetMapping("/member/findPwPage")
	public String findPwPage() {
		return "member/findPw";
	}
	@ResponseBody
	@GetMapping("/member/idEmailCheck")
	public Map<String, Object> idEmailCheck(MemberDTO member){
		return memberService.idEmailCheck(member);
	}
	@PostMapping("/member/changePw")
	public void changePw(HttpServletRequest request, HttpServletResponse response) {
		memberService.changePw(request, response);
	}

	
	
	
	
}
