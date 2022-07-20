package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import repository.StudentDAO;

public class ModifyService implements StudentService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String stuNo = request.getParameter("stuNo");
		String name = request.getParameter("name");
		Long kor = Long.parseLong(request.getParameter("kor"));
		Long eng = Long.parseLong(request.getParameter("eng"));
		Long mat = Long.parseLong(request.getParameter("mat"));
		double avg = (kor + eng + mat) / 3.0;
		String grade = null;
		if(avg >= 90) grade = "A";
		else if(avg >= 80) grade = "B";
		else if(avg >= 70) grade = "C";
		else if(avg >= 60) grade = "D";
		else grade = "F";
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("stuNo", stuNo);
		map.put("name", name);
		map.put("kor", kor + "");
		map.put("eng", eng + "");
		map.put("mat", mat + "");
		map.put("avg", avg + "");
		map.put("grade", grade);
		
		int res = StudentDAO.getInstance().updateStudent(map);
		
		ActionForward af = null;
		if(res > 0) {
			af = new ActionForward("/BATCH/detail.do?stuNo=" + stuNo, true);
		} else {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('학생 수정 실패')");
			out.println("history.back()");
			out.println("</script>");
			out.flush();
			out.close();
		}		
		
		return af;
		
		
	}

}
