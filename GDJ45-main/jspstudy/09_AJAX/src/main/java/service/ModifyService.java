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
		
		// 요청 Parameter
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String address = request.getParameter("address");
		
		MemberDTO member = MemberDTO.builder()
				.id(id)
				.name(name)
				.gender(gender)
				.address(address)
				.build();
		
		// 응답 메시지 형식
		// JSON
		response.setContentType("application; charset=UTF-8");
		
		// 응답 메시지 예시
		// 성공 {"res": 1}
		// 실패 {"res": 0}
		JSONObject obj = new JSONObject();
		obj.put("res", MemberDAO.getInstance().updateMember(member));
		
		// 응답하기
		PrintWriter out = response.getWriter();
		out.write(obj.toString());
		out.flush();
		out.close();
		
	}

}
