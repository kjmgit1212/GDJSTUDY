package service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import repository.StudentDAO;

public class ListService implements StudentService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setAttribute("list", StudentDAO.getInstance().selectStudentList());
		request.setAttribute("totalCount", StudentDAO.getInstance().getTotalCount());
		request.setAttribute("totalAverage", StudentDAO.getInstance().getTotalAverage());
		return new ActionForward("student/list.jsp", false);
	}

}
