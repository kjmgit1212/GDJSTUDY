package service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import repository.EmpDAO;

public class RemoveService implements EmpService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		// 요청 파라미터
		Long empNo = Long.parseLong(request.getParameter("empNo"));
		
		// deleteEmp() 메소드 호출 : DB 삭제
		int res = EmpDAO.getInstance().deleteEmp(empNo);
		
		// 성공 메시지, 실패 메시지
		try {
			PrintWriter out = response.getWriter();
			if(res > 0) {
				out.println("<script>");
				out.println("alert('사원 정보가 삭제되었습니다.')");
				out.println("location.href='/DBCP/list.do'");
				out.println("</script>");
				out.flush();
				out.close();
			} else {
				out.println("<script>");
				out.println("alert('사원 정보가 삭제되지 않았습니다.')");
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
