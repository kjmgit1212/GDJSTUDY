package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.ActionForward;
import repository.BoardDAO;

public class ListService implements BoardService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		// 상세보기할 때 session에 올려 둔 board, open 속성을 제거
		HttpSession session = request.getSession();
		if(session.getAttribute("board") != null) {
			session.removeAttribute("board");
		}
		if(session.getAttribute("open") != null) {
			session.removeAttribute("open");
		}
		
		request.setAttribute("list", BoardDAO.getInstance().selectBoardList());
		request.setAttribute("totalCount", BoardDAO.getInstance().selectBoardCount());
		return new ActionForward("board/list.jsp", false);
	}

}
