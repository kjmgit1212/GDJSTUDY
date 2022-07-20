package service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.Emp;
import repository.EmpDAO;

public class AddService implements EmpService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		// 요청 파라미터
		String name = request.getParameter("name");
		String dept = request.getParameter("dept");
		
		// Emp emp 생성 (DTO 생성 : DB로 보낼 객체 생성)
		Emp emp = new Emp();
		emp.setName(name);
		emp.setDept(dept);
		
		// EmpDAO의 insertEmp() 메소드 호출 (DB에 넣기)
		// 1. 인수 : Emp emp (사원 1명의 정보)
		// 2. 반환 : int res (실패 0, 성공 1)
		int res = EmpDAO.getInstance().insertEmp(emp);
		
		// ActionForward 반환
		ActionForward af = null;
		if(res > 0) {
			af = new ActionForward("/DBCP/list.do", true);  // DML(INSERT, UPDATE, DELETE) 작업 이후에는 redirect 할 것.
			/*
				1.
					new ActionForward("emp/list.jsp", true);
					DB의 목록을 가져오지 않고 list.jsp로 그냥 이동하기 때문에 보여지는 내용이 없다.
				2.
					new ActionForward("/DBCP/list.do", true);
					list.do 매핑은 ListService를 실행시키는 매핑이다.
					ListService가 실행되면 DB의 목록을 가져와서 request에 속성으로 저장하고,
					list.jsp로 forward하기 때문에 목록이 보여진다.
			*/
		} else {
			// 어디로 어떻게 갈지 여기서 정한다.
			// 스크립트로 작업
			try {
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('사원 등록이 실패했습니다.')");
				out.println("history.back()");
				out.println("</script>");
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return af;  // 성공하면 af반환, 실패하면 null반환
		
	}

}
