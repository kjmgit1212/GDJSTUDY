package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.ActionForward;
import domain.Board;
import repository.BoardDAO;

public class DetailService implements BoardService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		// <a href="detail.do?no=1">제목</a>
		Optional<String> optNo = Optional.ofNullable(request.getParameter("no"));
		Long no = Long.parseLong(optNo.orElse("0"));
		
		// 상세보기를 하면 session에 "open" 속성을 저장하기로 한다.(값은 상관없음)
		// 수정은 상세보기를 한 다음에 진행하기 때문에 
		// 수정 이후 상세보기를 하는 경우에는 조회수를 늘리지 않는다.
		HttpSession session = request.getSession();
		if(session.getAttribute("open") == null) {
			session.setAttribute("open", true);
			// 조회수 늘리기
			// UPDATE 이후에는 redirect를 해야 한다. (물론 아몰랑~하고 forward 할 수도 있음)
			int res = BoardDAO.getInstance().updateHit(no);
			if(res == 0) {
				return new ActionForward("/DBCP_PRAC/list.do", true);
			}
		}
		
		// 조회 결과 가져오기
		Board board = BoardDAO.getInstance().selectBoardByNo(no);
		
		// 조회 결과가 있는 경우
		// 조회 결과 board를 request에 저장하면 redirect 할 때 전달되지 않으니까
		// session에 저장한다.
		if(board != null) {
			session.setAttribute("board", board);
			return new ActionForward("board/detail.jsp", true);
		}
		// 조회 결과가 없는 경우
		else {
			try {
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('조회 결과가 없습니다.')");
				out.println("history.back()");
				out.println("</script>");
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		
	}

}
