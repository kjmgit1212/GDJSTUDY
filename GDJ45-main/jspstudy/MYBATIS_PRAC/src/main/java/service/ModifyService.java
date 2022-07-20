package service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.BoardDTO;
import repository.BoardDAO;

public class ModifyService implements BoardService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		Long no = Long.parseLong(request.getParameter("no"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		BoardDTO board = BoardDTO.builder()
				.no(no)
				.title(title)
				.content(content)
				.build();
		
		int res = BoardDAO.getInstance().updateBoard(board);
		
		try {
			
			PrintWriter out = response.getWriter();
			
			if(res > 0) {
				out.println("<script>");
				out.println("alert('수정 성공')");
				out.println("location.href='detail.do?no=" + no + "'");
				out.println("</script>");
				out.flush();
				out.close();
			} else {
				out.println("<script>");
				out.println("alert('수정 실패')");
				out.println("history.back()");
				out.println("</script>");
				out.flush();
				out.close();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}

}
