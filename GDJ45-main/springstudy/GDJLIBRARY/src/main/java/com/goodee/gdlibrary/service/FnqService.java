package com.goodee.gdlibrary.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface FnqService {
	
	// fnq 테이블 목록 + page 전역변수
	public Map<String, Object> getFnqList(int page);
	
	// 검색한 fnq 테이블 목록 + page 전역변수
	public Map<String, Object> search(HttpServletRequest request);
}
