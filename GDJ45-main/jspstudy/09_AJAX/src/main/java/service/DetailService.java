package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import domain.MemberDTO;
import repository.MemberDAO;

public class DetailService implements MemberService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// 요청 Parameter
		Optional<String> optNo = Optional.ofNullable(request.getParameter("no"));
		Long no = Long.parseLong(optNo.orElse("0"));
		
		// 응답 데이터 형식
		// JSON
		response.setContentType("application/json; charset=UTF-8");
		
		// 응답 데이터 예시(성공)
		// { 
		//   "result": true,
		//   "member": {
		//     "no": 1,
		//     "id": "user1",
		//     "name": "사용자1",
		//     "gender": "female",
		//     "address": "seoul"
		//   }
		// }
		
		// 응답 데이터 예시(실패)
		// {
		//   "result": false
		// }
		
		MemberDTO member = MemberDAO.getInstance().selectMemberByNo(no);
		boolean result = (member != null);
		/*
		 * boolean result = false; if(member != null) { result = true; }
		 */
		
		JSONObject obj = new JSONObject();
		obj.put("result", result);
		obj.put("member", member==null ? null : new JSONObject(member));
		
		// 응답하기
		PrintWriter out = response.getWriter();
		out.write(obj.toString());
		out.flush();
		out.close();
		
	}

}
