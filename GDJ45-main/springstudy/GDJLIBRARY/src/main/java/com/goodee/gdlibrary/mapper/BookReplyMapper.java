package com.goodee.gdlibrary.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.goodee.gdlibrary.domain.BookReplyDTO;

@Mapper
public interface BookReplyMapper {
	
	
	// 감상평 등록
	public int insertReply(BookReplyDTO reply);
	// 대여기록체크
	public int selectRentCheck(BookReplyDTO reply);
	public int selectReturnCheck(BookReplyDTO reply);
	public int selectOverdueCheck(BookReplyDTO reply);
	
	
	// 등록된 감상평목록 가져오기
	public List<BookReplyDTO> selectReplyList(Map<String, Object> map);
	
	// 책별 감상평 숫자
	public int selectReplyCount(Long bookNo);
	
	// 책별 평균별점
	public Double selectReplyRatingAverage(Long bookNo);

	
	
	
}
