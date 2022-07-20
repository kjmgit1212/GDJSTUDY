package com.goodee.gdlibrary.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goodee.gdlibrary.service.BookReplyService;

@Controller
public class BookReplyController {

	@Autowired
	private BookReplyService bookReplyService;
	
	// 감상평 추가
	@ResponseBody
	@GetMapping(value="/reply/save", produces="application/json")
	public Map<String, Object> addReply(HttpServletRequest request) {
		
	 	return bookReplyService.addReply(request);
	}
	
	// 책별 감상평 리스트
	@ResponseBody
	@GetMapping(value="/reply/list", produces="application/json; charset=UTF-8")
	public Map<String, Object> list(HttpServletRequest request) {
		
		return bookReplyService.bookReplyList(request); 
	}
	
}
