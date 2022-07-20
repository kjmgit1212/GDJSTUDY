package service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import repository.StudentDAO;

public class SearchService implements StudentService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		int begin = Integer.parseInt(request.getParameter("begin"));
		int end = Integer.parseInt(request.getParameter("end"));
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("begin", begin);
		map.put("end", end);
		
		request.setAttribute("list", StudentDAO.getInstance().selectStudentByAvg(map));
		request.setAttribute("totalCount", StudentDAO.getInstance().getSearchCount(map));
		request.setAttribute("totalAverage", StudentDAO.getInstance().getSearchAverage(map));
		
		return new ActionForward("student/list.jsp", false);
		
	}

}
