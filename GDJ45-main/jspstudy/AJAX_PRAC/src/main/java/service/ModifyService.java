package service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import domain.MemberDTO;
import repository.MemberDAO;

public class ModifyService implements MemberService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		MemberDTO member = MemberDTO.builder()
				.id(request.getParameter("id"))
				.name(request.getParameter("name"))
				.address(request.getParameter("address"))
				.build();
		
		int res = MemberDAO.getInstance().updateMember(member);
		
		JSONObject obj = new JSONObject();
		obj.put("res", res);
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(obj.toString());
		out.flush();
		out.close();

	}

}
