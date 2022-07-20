package service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import domain.MemberDTO;
import repository.MemberDAO;

public class DetailService implements MemberService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		Long no = Long.parseLong(request.getParameter("no"));
		
		MemberDTO member = MemberDAO.getInstance().selectMemberByNo(no);
		
		JSONObject obj = new JSONObject(member);
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(obj.toString());
		out.flush();
		out.close();

	}

}
