package com.goodee.gdlibrary.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface BookService {
	
	// api정보 받아오기
	public void getBooksInfo(HttpServletRequest request);
	
	// 책목록 출력
	public Map<String, Object> bookList(int page);
	
	// 책검색하기
	public Map<String, Object> searchBook(HttpServletRequest request);
	
	// 책 상세보기
	public void detailBook(HttpServletRequest request, Model model);
	
	// 평점별 추천도서
	public Map<String, Object> recomBook(Model model);
	
}
