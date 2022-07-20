package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import repository.BoardDAO;

public class ListService implements BoardService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		request.setAttribute("boards", BoardDAO.getInstance().selectBoardList());
		request.setAttribute("count", BoardDAO.getInstance().getBoardCount());
		return new ActionForward("board/list.jsp", false);
		
	}

}
