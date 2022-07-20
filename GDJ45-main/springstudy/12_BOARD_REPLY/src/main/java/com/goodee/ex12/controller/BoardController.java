package com.goodee.ex12.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.goodee.ex12.domain.BoardDTO;
import com.goodee.ex12.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;

	@GetMapping("/board/list")
	public String list(HttpServletRequest request, Model model) {
		request.getSession().removeAttribute("board");
		boardService.findBoards(request, model);
		return "board/list";
	}
	
	@GetMapping("/board/savePage")
	public String savePage() {
		return "board/save";
	}
	
	@PostMapping("/board/save")
	public String save(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("insRes", boardService.save(request));
		redirectAttributes.addFlashAttribute("type", "insert");
		return "redirect:/board/result";  // 매핑 /board/result로 redirect 하겠다.
	}
	
	@GetMapping("/board/result")
	public String result() {
		return "board/result";  // board/result.jsp로 이동하겠다.
	}
	
	@GetMapping("/board/detail")
	public String detail(HttpServletRequest request, HttpServletResponse response, Model model) {
		boardService.findBoardByNo(request, response, model);
		return "board/detail";
	}
	
	@GetMapping("/board/changePage")
	public String changePage() {
		return "board/change";
	}
	
	@PostMapping("/board/change")
	public String change(BoardDTO board, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("updRes", boardService.change(board));
		redirectAttributes.addFlashAttribute("type", "update");
		return "redirect:/board/result";
	}
	
	@GetMapping("/board/remove")
	public String remove(@RequestParam(value="boardNo", required=false, defaultValue="0L") Long boardNo
					, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("delRes", boardService.remove(boardNo));
		redirectAttributes.addFlashAttribute("type", "delete");
		return "redirect:/board/result";
	}
	
}
