package service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.Emp;
import repository.EmpDAO;

public class ListService implements EmpService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		// DB에서 모든 사원 목록 가져오기
		List<Emp> list = EmpDAO.getInstance().selectEmpList();
		
		// jsp로 전달할 데이터는 request에 속성으로 저장한다.
		request.setAttribute("list", list);
		request.setAttribute("empCount", list.size());
		
		// list.jsp로 request를 전달한다. (forward)
		return new ActionForward("emp/list.jsp", false);
		
	}

}
