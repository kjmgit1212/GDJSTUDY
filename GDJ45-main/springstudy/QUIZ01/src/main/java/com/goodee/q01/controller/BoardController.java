package com.goodee.q01.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.goodee.q01.domain.BoardDTO;
import com.goodee.q01.sevice.BoardService;

@Controller
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService boardService;
		
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/board/list")
	public String list(Model model) {
		List<BoardDTO> boards = boardService.findBoards();
		Long count = boardService.getBoardCount();
		logger.info("list(): " + count + "=>" + boards);
		model.addAttribute("boards", boards);
		model.addAttribute("count", count);
		return "board/list";
	}
	
	@GetMapping("/board/detail")
	public String detail(@RequestParam(value="no") Long no, Model model) {
		boardService.increseHit(no);  // 조회수 증가
		BoardDTO board = boardService.findBoardByNo(no);  // 증가된 조회수로 가져올 수 있음
		logger.info("detail(): " + board);
		model.addAttribute("board", board);
		return "board/detail";
	}
	
	@GetMapping("/board/savePage")
	public String savePage() {
		return "board/save";
	}
	
	@PostMapping("/board/save")
	public String save(BoardDTO board, HttpServletRequest request) {
		board.setIp(request.getRemoteAddr());  // board에 IP 넣기
		logger.info("save(): " + board);
		boardService.save(board);
		return "redirect:/board/list";
	}
	
	@GetMapping("/board/changePage")
	public String changePage(@RequestParam(value="no", required=false, defaultValue="0") Long no, Model model) {
		logger.info("changePage(): " + no);
		model.addAttribute("board", boardService.findBoardByNo(no));
		return "board/change";
	}
	
	@PostMapping("/board/change")
	public String change(BoardDTO board) {
		logger.info("change(): " + board);
		boardService.change(board);
		return "redirect:/board/list";
	}
	
	@GetMapping("/board/remove")
	public String remove(@RequestParam(value="no", required=false, defaultValue="0") Long no) {
		logger.info("remove(): " + no);
		boardService.remove(no);
		return "redirect:/board/list";
	}
	
}
