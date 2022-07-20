package service;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import repository.BoardDAO;

public class DetailService implements BoardService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
	
		Optional<String> opt = Optional.ofNullable(request.getParameter("no"));
		Long no = Long.parseLong(opt.orElse("0"));
		request.setAttribute("board", BoardDAO.getInstance().selectBoardByNo(no));
		return new ActionForward("board/detail.jsp", false);
	
	}

}
