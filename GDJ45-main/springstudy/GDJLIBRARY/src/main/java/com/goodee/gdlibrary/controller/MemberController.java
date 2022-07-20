package com.goodee.gdlibrary.controller;

import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.goodee.gdlibrary.domain.MemberDTO;
import com.goodee.gdlibrary.service.MemberService;
import com.goodee.gdlibrary.util.SecurityUtils;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;

	//약관 동의 페이지 이동
	@GetMapping("/member/agreePage")
	public String agreePage() {
		return "member/agree";
	}
	
	//아이디 중복 체크(회원, 휴면)
	@ResponseBody
	@GetMapping(value="/member/idCheck", produces="application/json")
	public Map<String, Object> idCheck(@RequestParam String memberId){
		return memberService.idCheck(memberId);
	}
	
	//이메일 중복 체크(회원, 휴면)
	@ResponseBody
	@GetMapping(value="/member/emailCheck", produces="application/json")
	public Map<String, Object> emailCheck(@RequestParam String memberEmail){
		return memberService.emailCheck(memberEmail);
	}
	
	//인증 이메일 전송
	@ResponseBody
	@GetMapping(value="/member/sendAuthCode", produces="application/json")
	public Map<String, Object> sendAuthCode(@RequestParam String memberEmail) {
		return memberService.sendAuthCode(memberEmail);
		
	}
	
	//회원가입 페이지
	@GetMapping("/member/signInPage")
	public String signInPage(@RequestParam(required=false) String promotion, Model model) {
		model.addAttribute("promotion", promotion);
		return "member/signIn";
	}
	
	//회원가입
	@RequestMapping(value="/member/signIn" , method = {RequestMethod.GET, RequestMethod.POST})
	public void signIn(HttpServletRequest request, HttpServletResponse response) {
		memberService.signIn(request, response);
	}

	//로그인 페이지 이동
	@GetMapping("/member/loginPage")
	public String loginPage(@RequestParam(required=false) String url, HttpServletRequest request, Model model) {
		model.addAttribute("url", url);	
		String apiURL = memberService.getNaverURL(request);
		model.addAttribute("apiURL", apiURL);
		return "member/login";
	}
	
	//로그인
	@PostMapping("/member/login")
	public void login(HttpServletRequest request, Model model) {
		
		MemberDTO loginMember = memberService.login(request);
		
		if(loginMember != null) {
			model.addAttribute("loginMember", loginMember);
		}
		
		model.addAttribute("url", request.getParameter("url"));
		
		String keepLogin = request.getParameter("keepLogin");
		if(keepLogin != null && keepLogin.equals("keep")) {
			memberService.keepLogin(request);
		}
		
	}

	//로그아웃
	@GetMapping("/member/logout")
	public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		
		MemberDTO loginMember = (MemberDTO)session.getAttribute("loginMember");
		if(loginMember != null) {
			session.removeAttribute("loginMember");
			session.invalidate();
			
			Cookie cookie = WebUtils.getCookie(request, "keepLogin");
			if(cookie != null) {
				cookie.setPath("/");
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
		}
		return "redirect:/";  
	}
	
	//마이페이지 이동
	@GetMapping("/member/myPage")
	public String myPage() {
		
		return "member/myPage";
	}
	
	//아이디 찾기 페이지 이동
	@GetMapping("/member/findIdPage")
	public String findIdPage() {
		return "member/findIdPage";
	}
	
	//아이디 찾기
	@ResponseBody
	@PostMapping(value="/member/findId", produces="application/json")
	public Map<String, Object> findId(MemberDTO member){
		return memberService.findId(member);
	}
	
	//비밀번호 찾기 페이지 이동
	@GetMapping("/member/findPwPage")
	public String findPwPage() {
		return "member/findPwPage";
	}
	
	
	//비밀번호를 찾기 위한 아이디/이메일 확인
	@ResponseBody
	@GetMapping(value="/member/findPwCheckIdEmail", produces="application/json")
	public Map<String, Object> findPwCheckIdEmail(HttpServletRequest request) {
		return memberService.findPwCheckIdEmail(request);
	}
	
	//비밀번호 변경 페이지로 이동
	@PostMapping("/member/findPw")
	public String findPw(HttpServletRequest request, Model model) {
		String memberId = SecurityUtils.xss(request.getParameter("memberId"));
		model.addAttribute("memberId", memberId);
		return "member/findPw";
	}
	
	//비밀번호 변경
	@PostMapping("/member/changePw")
	public void changePw(HttpServletRequest request, HttpServletResponse response) {
		memberService.changePw(request, response);
	}
	
	
	//회원 정보 수정 비밀번호 확인 페이지 이동
	@GetMapping("/member/modifyConfirm")
	public String modifyConfirm() {
		return "member/modifyConfirm";
	}

	//회원정보 수정 비밀번호 확인
	@PostMapping("/member/modifyPwCheck")
	public void modifyPwCheck(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		memberService.modifyPwCheck(session, request, response);
	}
	
	//회원 정보 수정 페이지 이동
	@GetMapping("/member/modifyPage")
	public String modifyPage(@RequestParam String memberId, Model model) {
		memberService.modifyPage(memberId, model);
		return "member/modifyPage";
	}
	
	//회원 정보 수정
	@PostMapping("/member/modify")
	public void modify(HttpServletRequest request, HttpServletResponse response) {
		memberService.modify(request, response);
	}
	
	//회원 탈퇴
	@PostMapping("/member/memberDelete")
	public void memberDelete(@RequestParam String memberId, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		memberService.memberDelete(memberId, session, request, response);
	}
	
	//회원 비밀번호 변경 확인 페이지 이동
	@GetMapping("/member/pwModifyConfirm")
	public String pwModifyConfirm() {
		return "member/pwModifyConfirm";
	}
	
	//회원 비밀번호 변경 확인
	@PostMapping("/member/pwChangePwCheck")
	public void pwChangePwCheck(HttpServletRequest request, HttpServletResponse response) {
		memberService.pwChangePwCheck(request, response);
	}
	
	//회원 비밀번호 변경 페이지 이동
	@GetMapping("/member/pwModifyPage")
	public String pwModifyPage() {
		return "member/pwModifyPage";
	}
	
	//회원 비밀번호 변경
	@PostMapping("/member/pwModify")
	public void pwModify(HttpServletRequest request, HttpServletResponse response) {
		memberService.pwModify(request, response);
	}

	//네아로 콜백
	@GetMapping("/member/callback")
	public String callback(HttpServletRequest request, Model model) {
		
		String accessToken = memberService.getNaverAccessToken(request);
		Map<String, Object> naverUserInfo = memberService.getNaverUserInfo(accessToken);
		MemberDTO loginMember = memberService.naverLogin(request, naverUserInfo);

		if(loginMember != null) {
			model.addAttribute("loginMember", loginMember);
		}
		return "index";
		
	}

	//휴면테이블 페이지 이동
	@PostMapping("/member/dormantCancelPage")
	public String dormantCancelPage() {
		return "member/dormantCancelPage";
	}
	
	//휴면 회원 취소
	@PostMapping("/member/dormantCancel")
	public void dormantCancel(HttpServletRequest request, HttpServletResponse response) {
		memberService.dormantCancle(request, response);
	}
	
	//지도
	@GetMapping("/member/map")
	public String text() {
		return "/member/map";
	}
	
	//마이페이지 대여 목록
	@ResponseBody
	@GetMapping(value="/member/rentBookList", produces="application/json")
	public Map<String, Object> rentBookList(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<>();
		result.put("rentBookList", memberService.rentBookList(request));
		return result;
	}
	
	//마이페이지 연체 목록
	@ResponseBody
	@GetMapping(value="/member/overdueBookList", produces="application/json")
	public Map<String, Object> overdueBookList(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<>();
		result.put("overdueBookList", memberService.overdueBookList(request));
		return result;
	}
	
	//마이페이지 예약한 좌석
	@ResponseBody
	@GetMapping(value="/member/reservationSeatNo", produces="application/json")
	public Map<String, Object> reservationSeatNo(HttpServletRequest request){
		
		return memberService.reservationSeatNo(request);
	}
	
	
	
}
