package model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Now implements MyService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		// 응답
		String now = new SimpleDateFormat("a h:mm:ss").format(new Date());
		
		// 응답 결과는 request에 속성(attribute)으로 저장한다.
		request.setAttribute("result", now);
		
		// 어디로 어떻게 갈 것인가?
		ActionForward af = new ActionForward();
		af.setView("views/output.jsp");
		af.setRedirect(false);  // forward 하겠다.
		
		// ActionForward 반환
		return af;
		
	}
	
}
