package com.goodee.ex15.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.goodee.ex15.domain.MemberDTO;
import com.goodee.ex15.domain.SignOutMemberDTO;

@Mapper
public interface MemberMapper {
	public MemberDTO selectMemberById(String id);
	public MemberDTO selectMemberByEmail(String email);
	public int insertMember(MemberDTO member);
	public int deleteMember(Long memberNo);
	public MemberDTO selectMemberByIdPw(MemberDTO member);
	public int insertMemberLog(String id);
	public SignOutMemberDTO selectSignOutMemberById(String id);
	public int reInsertMember(MemberDTO member);
	public int deleteSignOutMember(String id);
	public int updateSessionInfo(MemberDTO member);
	public MemberDTO selectMemberBySessionId(String sessionId);
	/* 아이디 찾기 */
	public MemberDTO selectMemberByNameEmail(MemberDTO member);
	/* 비밀번호 찾기 */
	public MemberDTO selectMemberByIdEmail(MemberDTO member);
	public int updatePw(MemberDTO member);
}
