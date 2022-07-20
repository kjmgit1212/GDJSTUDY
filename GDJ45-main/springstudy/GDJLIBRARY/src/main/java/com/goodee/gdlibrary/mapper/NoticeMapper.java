package com.goodee.gdlibrary.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.goodee.gdlibrary.domain.NoticeDTO;
import com.goodee.gdlibrary.domain.NoticeFileAttachDTO;

@Mapper
public interface NoticeMapper {
	
	// 공지사항 테이블 전체 목록 개수
	public Long selectNoticeCount();
	
	// 공지사항 테이블의 목록 가져오기
	public List<NoticeDTO> selectNotices(Map<String, Object> map);
	
	// 공지사항 게시글 삽입
	public int insertNotice(NoticeDTO notice);
	// 공지사항 파일 첨부
	public int insertFileAttach(NoticeFileAttachDTO noticeFileAttach);
	
	// 공지사항 조회수 증가
	public void updateHit(Long noticeNo);
	
	// 공지사항 게시글 상세보기
	public NoticeDTO selectNoticeByNoticeNo(Long noticeNo);
	public List<NoticeFileAttachDTO> selectFileAttachListInTheNotice(Long noticeNo);
	public NoticeFileAttachDTO selectFileAttachByNo(Long noticeFileAttachNo);
	
	// 공지사항 게시글 삭제하기
	public int deleteNoticeByNoticeNo(Long noticeNo);
	
	// 공지사항 게시글 수정하기
	public int updateNoticeByNoticeNo(NoticeDTO notice);
	
	// 공지사항 게시글 검색하기
	public long selectFindCount(Map<String, Object> map);
	
	// 공지사항 검색한 게시글 목록 가져오기
	public List<NoticeDTO> selectFindList(Map<String, Object> map);
	
	// 공지사항 첨부 파일 삭제
	public int deleteFileAttach(Long noticeFileAttachNo);
	
	// 스케쥴러 사용을 위한 SQL
	// 어제 저장된 첨부 파일 목록
	public List<NoticeFileAttachDTO> selectFileAttachListAtYesterday();
	

}
