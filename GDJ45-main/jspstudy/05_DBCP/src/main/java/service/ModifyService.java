package service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.Emp;
import repository.EmpDAO;

public class ModifyService implements EmpService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		// 요청 파라미터
		Long empNo = Long.parseLong(request.getParameter("empNo"));
		String name = request.getParameter("name");
		String dept = request.getParameter("dept");
		
		// DB로 보낼 DTO 생성(Emp emp)
		Emp emp = new Emp();
		emp.setEmpNo(empNo);
		emp.setName(name);
		emp.setDept(dept);
		
		// updateEmp() 메소드 호출 (DB 수정)
		int res = EmpDAO.getInstance().updateEmp(emp);
		
		// 성공 메시지, 실패 메시지
		// 성공하면 location으로 이동(redirect 개념)
		// 실패하면 history로 이동(어떤 이동이든 상관 없음)
		
		// ActionForward 반환은 어떻게 할 것인가?
		// null 값을 반환한다.
		// 성공/실패 모두 이미 이동했기 때문에 ActionForward로 이동할 수 없다.
		
		try {
			PrintWriter out = response.getWriter();
			if(res > 0) {
				out.println("<script>");
				out.println("alert('사원 정보가 수정되었습니다.')");
				// out.println("location.href='/DBCP/list.do'");  // 목록
				out.println("location.href='/DBCP/detail.do?empNo=" + empNo + "'");  // 상세
				out.println("</script>");
				out.flush();
				out.close();
			} else {
				out.println("<script>");
				out.println("alert('사원 정보가 수정되지 않았습니다.')");
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
