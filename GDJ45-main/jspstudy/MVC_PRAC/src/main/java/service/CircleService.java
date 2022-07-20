package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;

public class CircleService implements MyService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		// 요청 파라미터
		String strRadius = request.getParameter("radius");
		double radius = Double.parseDouble(strRadius);
		
		// 결과는 request에 속성으로 저장(반지름, 넓이)
		request.setAttribute("radius", radius);
		request.setAttribute("area", Math.PI * Math.pow(radius, 2));
		
		// ActionForward 생성 및 반환
		ActionForward af = new ActionForward();
		af.setView("views/circle.jsp");
		af.setRedirect(false);  // forward (request 전달을 위해서)
		
		return af;
		
	}

}