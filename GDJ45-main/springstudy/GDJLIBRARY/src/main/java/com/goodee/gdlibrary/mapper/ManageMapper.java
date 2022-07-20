package com.goodee.gdlibrary.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.goodee.gdlibrary.domain.BookDTO;
import com.goodee.gdlibrary.domain.DormantMemberDTO;
import com.goodee.gdlibrary.domain.FnqDTO;
import com.goodee.gdlibrary.domain.MemberDTO;
import com.goodee.gdlibrary.domain.SignOutMemberDTO;

@Mapper
public interface ManageMapper {
	
	// 회원 수, 회원 목록
	public int selectMemberCount();
	public List<MemberDTO> selectMemberList(Map<String, Object> map);

	// 회원 상세 보기
	public MemberDTO selectMemberByNo(Long memberNo);
	
	// 회원 ID체크
	public MemberDTO selectMemberById(String id);
	// 회원 EMAIL체크
	public MemberDTO selectMemberByEmail(String email);
	
	// 회원 수정
	public int updateMember(MemberDTO member);
	
	// 회원 선택삭제
	public int deleteCheckMember(List<String> list);
	
	// 활동회원 추가
	public int insertMember(MemberDTO member);
	
	// 회원 삭제
	public int deleteMember(Long memberNo);
	
	// 회원 검색
	public int selectFindMemberCount(Map<String, Object> map);
	public List<MemberDTO> selectFindMemberList(Map<String, Object> map);
	
	// 회원 검색 자동완성
	public List<MemberDTO> autoCompleteMember(Map<String, Object> map);
	
	
	
	// 휴면회원 수, 회원 목록
	public int selectDormantMemberCount();
	public List<DormantMemberDTO> selectDormantMemberList(Map<String, Object> map);

	// 휴면회원 상세 보기
	public DormantMemberDTO selectDormantMemberByNo(Long memberNo);
	
	// 휴면회원 ID체크
	public DormantMemberDTO selectDormantMemberById(String id);
	// 휴면회원 EMAIL체크
	public DormantMemberDTO selectDormantMemberByEmail(String email);
	
	// 휴면회원 선택삭제
	public int deleteCheckDormantMember(List<String> list);
	
	// 휴면회원 추가
	public int insertDormantMember(DormantMemberDTO dormantMember);
	
	// 휴면->활동회원 전환
	public int insertDormantToMember(MemberDTO member);
	
	// 휴면회원 삭제
	public int deleteDormantMember(Long memberNo);
	
	// 휴면회원 검색
	public int selectFindDormantMemberCount(Map<String, Object> map);
	public List<DormantMemberDTO> selectFindDormantMemberList(Map<String, Object> map);
	
	// 휴면회원 검색 자동완성
	public List<DormantMemberDTO> autoCompleteDormantMember(Map<String, Object> map);
	
	
	
	// 회원 수, 회원 목록
	public int selectSignOutMemberCount();
	public List<SignOutMemberDTO> selectSignOutMemberList(Map<String, Object> map);

	// 회원 상세 보기
	public SignOutMemberDTO selectSignOutMemberByNo(Long memberNo);

	// 회원 선택삭제
	public int deleteCheckSignOutMember(List<String> list);
	
	// 탈퇴회원 추가
	public int insertSignOutMember(SignOutMemberDTO member);

	// 회원 삭제
	public int deleteSignOutMember(Long memberNo);
	
	// 회원 검색
	public int selectFindSignOutMemberCount(Map<String, Object> map);
	public List<SignOutMemberDTO> selectFindSignOutMemberList(Map<String, Object> map);
	
	// 회원 검색 자동완성
	public List<SignOutMemberDTO> autoCompleteSignOutMember(Map<String, Object> map);
	
	
	// 책 수, 책 목록
	public int selectBookCount();
	public List<BookDTO> selectBookList(Map<String, Object> map);

	// 책 상세 보기
	public BookDTO selectBookByNo(Long bookNo);
		
	// 책 ISBN체크
    public BookDTO checkBookByIsbn(String isbn);
	
	// 책 수정
	public int updateBook(BookDTO book);
		
	// 책 선택삭제
	public int deleteCheckBook(List<String> list);
	
	// 책 추가
	public int insertBook(BookDTO book);
	
	// 책 삭제
	public int deleteBook(Long bookNo);
	
	// 책 검색
	public int selectFindBookCount(Map<String, Object> map);
	public List<BookDTO> selectFindBookList(Map<String, Object> map);
	
	// 책 검색 자동완성
	public List<BookDTO> autoCompleteBook(Map<String, Object> map);
	
	// FNQ 게시글 추가
    public int insertFnq(FnqDTO fnq);
}
