package com.goodee.ex12.service;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.goodee.ex12.domain.BoardDTO;
import com.goodee.ex12.mapper.BoardMapper;
import com.goodee.ex12.util.PageUtils;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper boardMapper;
	
	@Override
	public void findBoards(HttpServletRequest request, Model model) {
		
//		int page = 1;
//		String strPage = request.getParameter("page");
//		if(strPage != null) {
//			page = Integer.parseInt(strPage);
//		}

		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1"));
		
		int totalRecord = boardMapper.selectBoardCount();
		
		PageUtils pageUtils = new PageUtils();
		pageUtils.setPageEntity(totalRecord, page);
		
		Map<String, Object> map = new HashMap<>();
		map.put("beginRecord", pageUtils.getBeginRecord());
		map.put("endRecord", pageUtils.getEndRecord());
		
		List<BoardDTO> boards = boardMapper.selectBoardList(map);
		
		model.addAttribute("boards", boards);
		model.addAttribute("totalRecord", totalRecord);
		model.addAttribute("paging", pageUtils.getPaging1(request.getContextPath() + "/board/list"));
		
	}
	
	@Override
	public void findBoardByNo(HttpServletRequest request, HttpServletResponse response, Model model) {
		
		Optional<String> opt = Optional.ofNullable(request.getParameter("boardNo"));
		Long boardNo = Long.parseLong(opt.orElse("0"));
		
		// 요청 주소
		// 상세보기 요청               : ${contextPath}/board/detail
		// 수정화면으로 가기 위한 요청 : ${contextPath}/board/changePage
		String requestURI = request.getRequestURI();
		if(requestURI.endsWith("detail")) {
			boardMapper.updateBoardHit(boardNo);
		}
		
		// 게시글 가져 옴(원글)
		BoardDTO board = boardMapper.selectBoardByNo(boardNo);
		
		// 게시글이 존재하면 session에 두기(상세보기, 수정할 때 꺼내 보려고)
		if(board != null) {	
			request.getSession().setAttribute("board", board);
		} else {
			try {
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('일치하는 게시글이 없습니다.')");
				out.println("history.back()");
				out.println("</script>");
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	@Transactional  // save() 메소드에 있는 모든 쿼리문은 모두 성공해야 합니다. 하나라도 실패하면 전부 실패처리 합니다.
	@Override
	public int save(HttpServletRequest request) {
		
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		// 어딘가를 경유해서 작성된 게시글의 IP는 요청헤더 X-Forwarded-For에 저장된다.
		// 직접 전달된 게시글은 요청헤더 X-Forwarded-For값이 없다.
		String ip = request.getHeader("X-Forwarded-For");
		if(ip == null) {
			ip = request.getRemoteAddr();
		}
		
		BoardDTO board = BoardDTO.builder()
				.writer(writer)
				.title(title)
				.content(content)
				.ip(ip)
				.build();
		
		return boardMapper.insertBoard(board);
		
	}
	
	@Override
	public int change(BoardDTO board) {
		return boardMapper.updateBoard(board);
	}
	
	@Override
	public int remove(Long boardNo) {
		return boardMapper.deleteBoard(boardNo);
	}
	
}
