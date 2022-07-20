package com.goodee.gdlibrary.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.goodee.gdlibrary.domain.DormantMemberDTO;
import com.goodee.gdlibrary.domain.MemberDTO;



public interface MemberService {
	
	//회원가입
	public Map<String, Object> idCheck(String memberId);
	public Map<String, Object> emailCheck(String memberEmail);
	public Map<String, Object> sendAuthCode(String memberEmail);
	public void signIn(HttpServletRequest request, HttpServletResponse response);
	
	//로그인
	public MemberDTO login(HttpServletRequest request);
	public void keepLogin(HttpServletRequest request);
	public MemberDTO getMemberBySessionId(String sessionId);
	
	//아이디 비번 찾기
	public Map<String, Object> findId(MemberDTO member);
	public Map<String, Object> findPwCheckIdEmail(HttpServletRequest request);
	public void changePw(HttpServletRequest request, HttpServletResponse response);
	
	//회원 정보 수정 체크
	public void modifyPwCheck(HttpSession session, HttpServletRequest request, HttpServletResponse response);
		
	//회원정보 수정
	public void modifyPage(String memberId, Model model);
	public void modify(HttpServletRequest request, HttpServletResponse response);
	
	//회원 탈퇴
	public void memberDelete(String memberId, HttpSession session, HttpServletRequest request, HttpServletResponse response);
	
	//회원 비밀번호 변경
	public void pwChangePwCheck(HttpServletRequest request, HttpServletResponse response);
	public void pwModify(HttpServletRequest request, HttpServletResponse response);
	
	
	
	//네아로
	public String getNaverURL(HttpServletRequest request);
	public String getNaverAccessToken (HttpServletRequest request);
	public Map<String, Object> getNaverUserInfo (String accessToken);
	public MemberDTO naverLogin(HttpServletRequest request, Map<String, Object> map);
	
	//휴면회원 취소(휴면 회원은 batch에서 함)
	public void dormantCancle(HttpServletRequest request, HttpServletResponse response);
	
	
	//마이페이지 대여
	public List<Map<String, Object>> rentBookList(HttpServletRequest request);
	//마이페이지 연체
	public List<Map<String, Object>> overdueBookList(HttpServletRequest request);
	//마이페이지 예약 좌석
	public Map<String, Object> reservationSeatNo(HttpServletRequest request);
	
}

