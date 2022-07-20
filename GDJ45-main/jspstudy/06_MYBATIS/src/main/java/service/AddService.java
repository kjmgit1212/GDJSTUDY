package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.Emp;
import repository.EmpDAO;

public class AddService implements EmpService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String name = request.getParameter("name");
		String dept = request.getParameter("dept");
		Integer salary = Integer.parseInt(request.getParameter("salary"));
		
		// Mybatis로 정보를 전달하는 경우에는 DTO 또는 Map을 사용한다.
		Emp emp = new Emp();
		emp.setName(name);
		emp.setDept(dept);
		emp.setSalary(salary);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("dept", dept);
		map.put("salary", salary);
		
		int res = EmpDAO.getInstance().insertEmp(emp);
		
		// insert, update, delete 이후에는 redirect 해야 한다.
		// 1. response.sendRedirect()
		// 2. location.href
		ActionForward af = null;
		if(res > 0) {
			af = new ActionForward("/MYBATIS/list.do", true);
		} else {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('사원 등록이 실패했습니다.')");
			out.println("history.back()");
			out.println("</script>");
			out.flush();
			out.close();
		}
		
		return af;
		
	}

}
