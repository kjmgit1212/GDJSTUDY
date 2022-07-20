package com.goodee.ex10.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.goodee.ex10.service.NoticeService;

@Controller
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/notice/list")
	public String list(Model model) {
		model.addAttribute("notices", noticeService.findNotices());
		return "notice/list";  // notice 폴더 아래 list.jsp로 이동
	}
	
	@GetMapping("/notice/savePage")
	public String savePage() {
		return "notice/save";  // notice 폴더 아래 save.jsp로 이동
	}
	
	@PostMapping("/notice/save")
	// public String save(@RequestParam String title, @RequestParam String content)
	// public String save(NoticeDTO notice)
	public String save(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		int res = noticeService.save(request);
		
		// 성공/실패 메시지 처리가 없는 경우
		// return "redirect:/notice/list";  // redirect는 매핑으로 이동한다. 목록보기매핑(/notice/list)

		// 성공/실패 메시지 처리를 담당하는 result.jsp를 만들고, 
		// result.jsp로 redirect로 이동하는 방법을 사용한다.
		// result.jsp로 성공/실패 유무, 작업종류를 보내줘야 하는데,
		// redirect로 이동하는 경우에는 RedirectAttributes에 정의된 addFlashAttribute() 메소드를 
		// 이용해서 값을 전달할 수 있다.
		redirectAttributes.addFlashAttribute("kind", "insert");
		redirectAttributes.addFlashAttribute("res", res);
		
		return "redirect:/notice/afterDML";
		
	}
	
	@GetMapping("/notice/afterDML")
	public String afterDML() {
		return "notice/result";   // notice 폴더 아래 result.jsp로 이동
	}
	
	@GetMapping("/notice/detail")
	public String detail(HttpServletRequest request, Model model) {
		model.addAttribute("notice", noticeService.findNoticeByNo(request));
		return "notice/detail";  // notice 폴더 아래 detail.jsp로 이동
	}
	
	@GetMapping("/notice/changePage")
	public String changePage(HttpServletRequest request, Model model) {
		model.addAttribute("notice", noticeService.findNoticeByNo(request));
		return "notice/change";  // notice 폴더 아래 change.jsp로 이동
	}
	
	@PostMapping("/notice/change")
	public String change(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("kind", "update");
		redirectAttributes.addFlashAttribute("res", noticeService.change(request));
		return "redirect:/notice/afterDML";
	}
	
	@GetMapping("/notice/removeOne")
	public String removeOne(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("kind", "deleteOne");
		redirectAttributes.addFlashAttribute("res", noticeService.removeOne(request));
		return "redirect:/notice/afterDML";
	}
	
	@GetMapping("/notice/removeList")
	public String removeList(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("kind", "deleteList");
		redirectAttributes.addFlashAttribute("res", noticeService.removeList2(request));
		return "redirect:/notice/afterDML";
	}
	
	@GetMapping("/notice/transaction.test")
	public void transactionTest() {
		noticeService.transactionTest();
	}
	
}
