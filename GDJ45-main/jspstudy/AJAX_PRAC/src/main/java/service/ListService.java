package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import domain.MemberDTO;
import repository.MemberDAO;

public class ListService implements MemberService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		List<MemberDTO> members = MemberDAO.getInstance().selectMemberList();
		int count = MemberDAO.getInstance().getMemberCount();
		
		JSONObject obj = new JSONObject();
		obj.put("count", count);
		obj.put("members", members);
		// {"count": 7, "members": [{}, {}, {}, {}, {}, {}, {}]}
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(obj.toString());
		out.flush();
		out.close();
		
	}

}
