package service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import repository.EmpDAO;

public class ListService implements EmpService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// request에 속성으로 목록을 저장하고
		// request 전달을 위한 forward 이동한다.
		request.setAttribute("list", EmpDAO.getInstance().selectEmpList());
		return new ActionForward("emp/list.jsp", false);
		
	}

}
