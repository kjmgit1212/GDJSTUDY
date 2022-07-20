package com.goodee.gdlibrary.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.goodee.gdlibrary.service.NoticeService;

@Controller
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	
	// 공지사항 페이지로 가기
	@GetMapping("/notice/noticePage")
	public String noticePage(HttpServletRequest request, Model model) {
		
		noticeService.getNotices(request, model);
		return "notice/notice";
	}
	
	// 글쓰기 버튼을 누르면 공지사항 게시글에 글 작성할 수 있음.
	// 관리자만 볼 수 있는 버튼임.
	// 공지사항 작성 페이지로 이동하기
	@GetMapping("/notice/addNoticePage")
	public String addNoticePage(HttpServletRequest request) {
		
		request.setAttribute("res", "add");
		return "notice/addNotice";
	}
	
	
	// summernote로 이미지 등록하기
	@ResponseBody
	@PostMapping(value="/notice/uploadSummernoteImage", produces="application/json")
	public Map<String, Object> uploadSummernoteImage(MultipartHttpServletRequest multipartRequest){
		return noticeService.uploadSummernoteImage(multipartRequest);
	}
	
	// summernote로 등록한 이미지 보여주기
	@ResponseBody
	@GetMapping("/notice/display")
	public ResponseEntity<byte[]> noticeDisplay(HttpServletRequest request) {
		return noticeService.display(request);
	}
	
	
	// 공지사항 게시글 등록하기
	@PostMapping("/notice/addNotice")
	public void addNotice(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) {
		noticeService.addNotice(multipartRequest, response);
	}
	
	
	// 공지사항 게시글 상세보기
	@GetMapping("/notice/detailNotice")
	public String detailNotice(HttpServletRequest request, Model model) {
		noticeService.detailNotice(request, model);
		return "notice/detailNotice";
	}
	
	// 공지사항 게시글에 올린 첨부파일 다운받기
	@ResponseBody
	@GetMapping("/notice/download")
	public ResponseEntity<Resource> download(@RequestHeader("User-Agent") String userAgent, @RequestParam Long noticeFileAttachNo) {
		return noticeService.download(userAgent, noticeFileAttachNo);
	}
	
	
	// 공지사항 게시글 삭제
	@GetMapping("/notice/removeNotice")
	public void removeNotice(HttpServletRequest request, HttpServletResponse response) {
		noticeService.removeNotice(request, response);
	}
	
	
	// 공지사항 수정 페이지로 가기
	// 수정 페이지는 따로 없고
	// addNotice.jsp 파일에서 
	// 공지사항 등록과 수정 기능을 같이 넣음.
	@GetMapping("/notice/modifyNoticePage")
	public String modifyNoticePage(HttpServletRequest request, Model model) {
		noticeService.modifyNoticePage(request, model);
		request.setAttribute("res", "modify");
		return "notice/addNotice";
	}
	
	// 공지사항 게시글 수정하기
	@PostMapping("/notice/modifyNotice")
	public void modifyNotice(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) {
		noticeService.modifyNotice(multipartRequest, response);
	}
	
	
	// 공지사항 게시글 검색하기
	@GetMapping("/notice/search")
	public String search(HttpServletRequest request, Model model) {
		noticeService.search(request, model);
		return "notice/notice";
	}
	
	
	// 기존 공지사항에 첨부된 파일 삭제하기(하나씩)
	@ResponseBody
	@GetMapping(value="/notice/removeFileAttach", produces="application/json")
	public Map<String, Object> removeFileAttach(@RequestParam Long noticeFileAttachNo, @RequestParam Long noticeNo) {
		return noticeService.removeFileAttach(noticeFileAttachNo, noticeNo);
	}
	
	
	
	
	
	
}
