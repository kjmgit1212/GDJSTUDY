package com.goodee.gdlibrary.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goodee.gdlibrary.domain.QaaDTO;
import com.goodee.gdlibrary.service.QaaService;

@Controller
public class QaaController {
	
	@Autowired
	private QaaService qaaService;
	
    // 질문과 답변 게시판으로 이동하기
	@GetMapping("/qaa/qaaPage")
	public String qaaPage(HttpServletRequest request, Model model) {
		
		qaaService.qaaList(request, model);
		return "qaa/qaa";
		
	}
	
	// 작성자(회원ID) 검색하기
	@GetMapping("/qaa/search")
	public String search(HttpServletRequest request, Model model) {
		qaaService.search(request, model);
		return "qaa/qaa";
	}
	
	
	// 회원만 qaa 게시글에 글 작성할 수 있음.
	// addQaaPage() 메소드 수행 전에 RequiredInterceptor의 preHandle() 메소드가 호출
	// 질문과 답변 작성 페이지로 이동하기
	@GetMapping("/qaa/addQaaPage")
	public String addQaaPage() {
		return "qaa/addQaa";
	}
	
	
	// qaa 게시판에 작성한 글 등록하기
	@PostMapping("/qaa/addQaa")
	public void addQaa(HttpServletRequest request, HttpServletResponse response) {
		qaaService.addQaa(request, response);
	}
	
	
	// qaa 게시판에 작성한 댓글 등록하기
	@PostMapping("/qaa/saveReply") 
	public void saveReply(HttpServletRequest request, HttpServletResponse response) {
		qaaService.saveReply(request, response);
	}
	
	
	// 회원이 작성한 원글 상세보기
	@GetMapping("/qaa/detailQaa")
	public String detailQaa(@RequestParam(value="qaaNo", required=false, defaultValue="0") Long qaaNo, Model model) {
		qaaService.detailQaa(qaaNo, model); 
		return "qaa/detailQaa";
	}

	
	// 회원이 작성한 글 수정하기 요청
	@PostMapping("/qaa/modifyQaa")
	public void modifyQaa(HttpServletRequest request, HttpServletResponse response) {
		qaaService.modifyQaa(request, response);
	}
	
	
	// 회원이 작성한 게시글(원본) 삭제
	// 원본 삭제하면서 원본에 달린 댓글도 같이 삭제됨.
	@GetMapping("/qaa/removeQaa")
	public void removeQaa(HttpServletRequest request, HttpServletResponse response) {
		qaaService.removeQaa(request, response);
	}
	
	
	// 댓글만 삭제하기
	@ResponseBody
	@PostMapping(value="/qaa/removeReply", produces="application/json")
	public Map<String, Object> removeReply(@RequestBody QaaDTO qaa) {
		return qaaService.removeReply(qaa);
	}
	
	
	
	
}
