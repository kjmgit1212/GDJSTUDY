package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.Board;
import repository.BoardDAO;

public class ModifyService implements BoardService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		Long no = Long.parseLong(request.getParameter("no"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		Board board = new Board();
		board.setNo(no);
		board.setTitle(title);
		board.setContent(content);
		
		int res = BoardDAO.getInstance().updateBoard(board);
		
		// 실행 결과를 updateResult.jsp로 넘김
		return new ActionForward("board/updateResult.jsp?res=" + res, true);
		
	}

}
