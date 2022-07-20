package com.goodee.gdlibrary.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface BookReplyService {
	
	// 감상평 등록
	public Map<String, Object> addReply(HttpServletRequest request);
	
	// 등록된 감상평목록 보기
	public Map<String, Object> bookReplyList(HttpServletRequest request);
	
	
}
