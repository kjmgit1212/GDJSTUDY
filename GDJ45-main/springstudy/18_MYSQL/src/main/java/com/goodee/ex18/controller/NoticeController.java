package com.goodee.ex18.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.goodee.ex18.service.NoticeService;

@Controller
public class NoticeController {

	@Autowired
	private NoticeService noticeService;

	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping(value="notice/list")
	public String list(HttpServletRequest request, Model model) {
		noticeService.getNotices(request, model);
		return "notice/list";
	}
	
	@GetMapping(value="notice/find")
	public String find(HttpServletRequest request, Model model) {
		noticeService.getFindNotices(request, model);
		return "notice/list";
	}
	
	@GetMapping(value="notice/detail")
	public String detail(@RequestParam Long noticeNo, Model model) {
		model.addAttribute("notice", noticeService.getNoticeByNo(noticeNo));
		return "notice/detail";
	}
	
	@GetMapping(value="notice/addPage")
	public String addPage() {
		return "notice/add";
	}
	
	@PostMapping(value="notice/add")
	public void add(HttpServletRequest request, HttpServletResponse response) {
		noticeService.addNotice(request, response);
	}
	
	@PostMapping(value="notice/modify")
	public void modify(HttpServletRequest request, HttpServletResponse response) {
		noticeService.modifyNotice(request, response);
	}
	
	@GetMapping(value="notice/remove")
	public void remove(HttpServletRequest request, HttpServletResponse response) {
		noticeService.removeNotice(request, response);
	}
	
}
