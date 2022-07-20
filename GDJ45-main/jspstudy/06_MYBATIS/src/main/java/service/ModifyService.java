package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import repository.EmpDAO;

public class ModifyService implements EmpService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", request.getParameter("name"));
		map.put("dept", request.getParameter("dept"));
		map.put("salary", Long.parseLong(request.getParameter("salary")));
		map.put("no", Long.parseLong(request.getParameter("no")));
		
		int res = EmpDAO.getInstance().updateEmp(map);
		
		ActionForward af = null;
		if(res > 0) {
			af = new ActionForward("/MYBATIS/list.do", true);
		} else {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('사원 수정이 실패했습니다.')");
			out.println("history.back()");
			out.println("</script>");
			out.flush();
			out.close();
		}
		
		return af;
		
	}

}
