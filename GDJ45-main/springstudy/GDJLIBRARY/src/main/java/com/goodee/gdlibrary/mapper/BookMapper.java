package com.goodee.gdlibrary.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.goodee.gdlibrary.domain.BookDTO;

@Mapper
public interface BookMapper {
	
		// api정보로 책추가
		public int insertBook(BookDTO book);
		
		
		// 등록된 책 숫자
		public int selectBookCount();
		// 책목록 출력
		public List<BookDTO> selectBookList(Map<String, Object> m);
		
		
		// 검색된 책숫자
		public int searchBookCount(Map<String, Object> map);
		// 책검색하기
		public List<BookDTO> searchBook(Map<String, Object> map);

		
		// 책 상세보기
		public BookDTO detailBook(Long bookNo);
		
		// 평점별 추천도서
		public List<BookDTO> recomBook();
		
}
