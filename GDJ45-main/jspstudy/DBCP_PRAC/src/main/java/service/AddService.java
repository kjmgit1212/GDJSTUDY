package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.Board;
import repository.BoardDAO;

public class AddService implements BoardService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		String writer = request.getParameter("writer");
		// String writer = (Member)(session.getAttribute("login")).getName();
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String ip = request.getRemoteAddr();
		
		Board board = new Board();
		board.setWriter(writer);
		board.setTitle(title);
		board.setContent(content);
		board.setIp(ip);
		
		int res = BoardDAO.getInstance().insertBoard(board);
		
		// 삽입 결과 페이지로 삽입 결과(res)를 보냄
		return new ActionForward("board/insertResult.jsp?res=" + res, true);
		
	}

}
