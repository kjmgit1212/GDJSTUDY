package com.goodee.ex05.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.goodee.ex05.domain.BoardDTO;
import com.goodee.ex05.service.BoardService;

/*
	@RestController
	
	안녕. 난 Ajax 처리를 위한 컨트롤러야.
	날 선언하면 모든 메소드에 @ResponseBody 애너테이션을 자동으로 붙여줄께.
	개발자 너희는 붙이지 마.
*/

@RestController

public class BoardController {

	private BoardService boardService;
	
	// 생성자는 @Autowired를 안 적어도 됩니다.
	public BoardController(BoardService boardService) {  // root-context.xml에 정의한 bean이 매개변수 BoardService boardService로 주입됩니다.
		super();
		this.boardService = boardService;
	}
	
	// @ResponseBody    @RestController를 사용하면 안 적어요.
	@GetMapping(value="/board/detail1",
			produces="application/json; charset=UTF-8")
	public BoardDTO detail1(HttpServletRequest request) {
		return boardService.detail1(request);
	}
	
	// @ResponseBody
	@GetMapping(value="/board/detail2",
			produces="application/json; charset=UTF-8")
	public BoardDTO detail2(String title, Long hit) {
		return boardService.detail2(title, hit);
	}
	
	// @ResponseBody
	@PostMapping(value="/board/detail3",
			produces="application/json; charset=UTF-8")
	public Map<String, Object> detail3(@RequestBody BoardDTO board) {
		return boardService.detail3(board);
	}
	
	// @ResponseBody
	@PostMapping(value="/board/detail4",
			produces="application/json; charset=UTF-8")
	public BoardDTO detail4(@RequestBody Map<String, Object> map) {
		return boardService.detail4(map);
	}
	
}
