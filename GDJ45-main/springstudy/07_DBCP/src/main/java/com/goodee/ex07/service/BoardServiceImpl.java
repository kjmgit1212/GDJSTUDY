package com.goodee.ex07.service;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.goodee.ex07.domain.BoardDTO;
import com.goodee.ex07.repository.BoardRepository;

public class BoardServiceImpl implements BoardService {

	
	// 서비스는 사용자의 요청을 데이터베이스로 전달하고,
	// 데이터베이스 처리 결과를 사용자에게 응답한다.
	
	
	// 서비스는 BoardConfig.java에서 BoardRepository bean을 가져와야 한다.(DI)
	@Autowired
	private BoardRepository boardRepository;
	
	
	@Override
	public List<BoardDTO> findBoards() {
		return boardRepository.selectBoards();
	}

	@Override
	public BoardDTO findBoardByNo(Long board_no) {
		return boardRepository.selectBoardByNo(board_no);
	}

	@Override
	public void save(BoardDTO board, HttpServletRequest request, HttpServletResponse response) {
		int res = boardRepository.insertBoard(board);
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			if(res > 0) {
				out.println("<script>");
				out.println("alert('등록되었습니다.')");
				out.println("location.href='" + request.getContextPath() + "/board/list'");  // location 이동은 redirect와 같은 방식의 이동이다.
				out.println("</script>");
				out.close();
			} else {
				out.println("<script>");
				out.println("alert('등록되지 않았습니다.')");
				out.println("history.back()");
				out.println("</script>");
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void modify(BoardDTO board, HttpServletRequest request, HttpServletResponse response) {
		int res = boardRepository.updateBoard(board);
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			if(res > 0) {
				out.println("<script>");
				out.println("alert('수정되었습니다.')");
				out.println("location.href='" + request.getContextPath() + "/board/list'");  // location 이동은 redirect와 같은 방식의 이동이다.
				out.println("</script>");
				out.close();
			} else {
				out.println("<script>");
				out.println("alert('수정되지 않았습니다.')");
				out.println("history.back()");
				out.println("</script>");
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void remove(Long board_no, HttpServletRequest request, HttpServletResponse response) {
		int res = boardRepository.deleteBoard(board_no);
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			if(res > 0) {
				out.println("<script>");
				out.println("alert('삭제되었습니다.')");
				out.println("location.href='" + request.getContextPath() + "/board/list'");  // location 이동은 redirect와 같은 방식의 이동이다.
				out.println("</script>");
				out.close();
			} else {
				out.println("<script>");
				out.println("alert('삭제되지 않았습니다.')");
				out.println("history.back()");
				out.println("</script>");
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
