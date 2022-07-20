package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;

public class RectangleService implements MyService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		// 요청 파라미터
		String strWidth = request.getParameter("width");
		int width = Integer.parseInt(strWidth);
		
		String strHeight = request.getParameter("height");
		int height = Integer.parseInt(strHeight);
		
		// 결과는 request에 속성으로 저장(너비, 높이, 넓이)
		request.setAttribute("width", width);
		request.setAttribute("height", height);
		request.setAttribute("area", width * height);
	
		// ActionForward 생성 및 반환
		ActionForward af = new ActionForward();
		af.setView("views/rectangle.jsp");
		af.setRedirect(false);  // forward (request 전달을 위해서)
		
		return af;
		
	}

}
