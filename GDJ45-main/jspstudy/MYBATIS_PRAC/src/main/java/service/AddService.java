package service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.BoardDTO;
import repository.BoardDAO;

public class AddService implements BoardService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		BoardDTO board = BoardDTO.builder()
				.writer(writer)
				.title(title)
				.content(content)
				.build();
		
		int res = BoardDAO.getInstance().insertBoard(board);
		
		try {
			
			PrintWriter out = response.getWriter();
			
			if(res > 0) {
				out.println("<script>");
				out.println("alert('삽입 성공')");
				out.println("location.href='list.do'");
				out.println("</script>");
				out.flush();
				out.close();
			} else {
				out.println("<script>");
				out.println("alert('삽입 실패')");
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
