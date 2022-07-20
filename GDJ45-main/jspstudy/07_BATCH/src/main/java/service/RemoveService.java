package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import repository.StudentDAO;

public class RemoveService implements StudentService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		Optional<String> optStuNo = Optional.ofNullable(request.getParameter("stuNo"));
		Long stuNo = Long.parseLong(optStuNo.orElse("0"));
		
		int res = StudentDAO.getInstance().deleteStudent(stuNo);
		
		ActionForward af = null;
		if(res > 0) {
			af = new ActionForward("/BATCH/list.do", true);
		} else {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('학생 삭제 실패')");
			out.println("history.back()");
			out.println("</script>");
			out.flush();
			out.close();
		}
		
		return af;
		
	}

}
