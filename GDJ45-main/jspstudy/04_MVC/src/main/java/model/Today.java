package model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Today implements MyService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		// 응답
		String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		
		// 응답 결과는 request에 속성(attribute)으로 저장한다.
		request.setAttribute("result", today);
		
		// 어디로 어떻게 갈 것인가?
		ActionForward af = new ActionForward();
		af.setView("views/output.jsp");
		af.setRedirect(false);  // forward 하겠다.
		
		// ActionForward 반환
		return af;
		
	}
	
}
