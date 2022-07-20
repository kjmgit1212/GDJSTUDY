package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import repository.BoardDAO;

public class RemoveService implements BoardService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		Optional<String> opt = Optional.ofNullable(request.getParameter("no"));
		Long no = Long.parseLong(opt.orElse("0"));
		
		int res = BoardDAO.getInstance().deleteBoard(no);
		
		try {
			
			PrintWriter out = response.getWriter();
			
			if(res > 0) {
				out.println("<script>");
				out.println("alert('삭제 성공')");
				out.println("location.href='list.do'");
				out.println("</script>");
				out.flush();
				out.close();
			} else {
				out.println("<script>");
				out.println("alert('삭제 실패')");
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
