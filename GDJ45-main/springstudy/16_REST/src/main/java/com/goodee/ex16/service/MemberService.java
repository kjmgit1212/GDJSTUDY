package com.goodee.ex16.service;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.goodee.ex16.domain.MemberDTO;

public interface MemberService {
	public Map<String, Object> addMember(MemberDTO member, HttpServletResponse response);
	public Map<String, Object> getMembers(int page);
	public Map<String, Object> getMember(Long memberNo);
	public Map<String, Object> changeMember(MemberDTO member, HttpServletResponse response);
	public Map<String, Object> removeMember(Long memberNo);
}
