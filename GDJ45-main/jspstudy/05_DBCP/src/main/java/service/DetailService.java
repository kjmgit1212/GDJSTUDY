package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.Emp;
import repository.EmpDAO;

public class DetailService implements EmpService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		// 요청 파라미터
		// /DBCP/detail.do?empNo=1  문제 없음
		// /DBCP/detail.do          empNo는 null
		
		Long empNo = 0L;  // 디폴트
		String strEmpNo = request.getParameter("empNo");
		if(strEmpNo != null) {
			empNo = Long.parseLong(strEmpNo);
		}
		
		// 사원상세정보 가져오기
		Emp emp = EmpDAO.getInstance().selectEmpByEmpNo(empNo);
		
		// detail.jsp로 사원상세정보 전달하기
		// 1. request에 속성으로 사원상세정보 저장하기
		// 2. request를 전달하는 forward
		request.setAttribute("emp", emp);
		
		return new ActionForward("emp/detail.jsp", false);
		
	}

}
