package com.goodee.ex15.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goodee.ex15.domain.MemberDTO;
import com.goodee.ex15.domain.SignOutMemberDTO;

public interface MemberService {
	public Map<String, Object> idCheck(String id);
	public Map<String, Object> emailCheck(String email);
	public Map<String, Object> sendAuthCode(String email);
	public void signIn(HttpServletRequest request, HttpServletResponse response);
	public void signOut(HttpServletRequest request, HttpServletResponse response);
	public MemberDTO login(HttpServletRequest request);
	public SignOutMemberDTO findSignOutMember(String id);
	public void reSignIn(HttpServletRequest request, HttpServletResponse response);
	public void keepLogin(HttpServletRequest request);
	public MemberDTO getMemberBySessionId(String sessionId);
	/* 아이디 찾기 */
	public Map<String, Object> findId(MemberDTO member);
	/* 비밀번호 찾기 */
	public Map<String, Object> idEmailCheck(MemberDTO member);
	public void changePw(HttpServletRequest request, HttpServletResponse response);
}