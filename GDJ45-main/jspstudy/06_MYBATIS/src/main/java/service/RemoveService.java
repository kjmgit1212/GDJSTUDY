package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import repository.EmpDAO;

public class RemoveService implements EmpService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		Optional<String> optNo = Optional.ofNullable(request.getParameter("no"));
		Long no = Long.parseLong(optNo.orElse("0"));
		
		int res = EmpDAO.getInstance().deleteEmp(no);
		
		ActionForward af = null;
		if(res > 0) {
			af = new ActionForward("/MYBATIS/list.do", true);
		} else {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('사원 삭제가 실패했습니다.')");
			out.println("history.back()");
			out.println("</script>");
			out.flush();
			out.close();
		}
		
		return af;
		
	}

}
