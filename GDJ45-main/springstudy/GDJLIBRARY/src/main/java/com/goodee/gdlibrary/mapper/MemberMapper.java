package com.goodee.gdlibrary.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.goodee.gdlibrary.domain.DormantMemberDTO;
import com.goodee.gdlibrary.domain.MemberDTO;
import com.goodee.gdlibrary.domain.MemberLogDTO;
import com.goodee.gdlibrary.domain.SeatDTO;

@Mapper
public interface MemberMapper {
	
	//회원 가입
	public MemberDTO selectMemberById(String memberId);
	public DormantMemberDTO selectDormantMemberById(String memberId);
	public MemberDTO selectMemberByEmail(String memberEmail);
	public DormantMemberDTO selectDormantMemberByEmail(String memberEmail);
	public int insertMemberSignIn(MemberDTO member);
	
	//로그인
	public MemberDTO selectMemberByIdPw(MemberDTO member);
	public MemberLogDTO selectMemberLog(String memberID);
	public int insertMemberLog(String memberId);
	public int updateMemberLog(String memberId);
	
	//로그인 세션
	public int updateSessionInfo(MemberDTO member);
	public MemberDTO selectMemberBySessionId(String sessionId);
	
	//아이디/비밀번호 찾기
	public MemberDTO selectMemberFindId(MemberDTO member);
	public DormantMemberDTO selectDormantMemberFindId(MemberDTO member);
	public MemberDTO selectMemberFindPwCheckIdEmail(MemberDTO member);
	public DormantMemberDTO selectDormantMemberFindPwCheckIdEmail(MemberDTO member);
	public int updateMemberChangePw(MemberDTO member);
	public int updateDormantMemberChangePw(MemberDTO member);
	
	//정보 수정
	public int updateMemberInfo(MemberDTO member);
	
	//회원탈퇴
	public int insertSignOut(MemberDTO member);
	public int deleteMember(String memberId);
	
	//휴면
	public List<MemberDTO> selectMemberLogSignIn();	
	public int insertDormant(MemberDTO member);
	public int deleteMemberInDormant(String memberId);
	
	//휴면 해제
	public int insertMemberDormantCancle(DormantMemberDTO dormant);
	public int deleteDormant(String memberId);
	
	//네아로 회원가입
	public int insertNaverLogin(MemberDTO member);
	public MemberDTO selectNaverLoginCheck(String memberID);
	
	//마이페이지 대여 목록
	public List<Map<String, Object>> selectRentBookList(String memberId);
	
	//마이페이지 연체 목록
	public List<Map<String, Object>> selectOverdueBookList(String memberId);
	
	//마이페이지 예약 좌석
	public SeatDTO selectReservationSeatNo(String memberID);
	
}

