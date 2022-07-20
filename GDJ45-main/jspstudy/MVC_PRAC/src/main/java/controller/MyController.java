package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import service.CircleService;
import service.MyService;
import service.RectangleService;

@WebServlet("*.do")
public class MyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public MyController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// URLMapping 확인
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length() + 1);
		
		// MyService service 인스턴스
		MyService service = null;
		
		// ActionForward af 인스턴스
		ActionForward af = null;
		
		// Model 생성
		switch(command) {
		// Model이 없는 단순 이동
		case "input.do":
			af = new ActionForward();
			af.setRedirect(false);
			af.setView("views/input.jsp");
			break;
		// Model이 필요한 작업
		case "rectangle.do":
			service = new RectangleService();
			break;
		case "circle.do":
			service = new CircleService();
			break;
		}
		
		// Model 실행
		if(service != null) {
			af = service.execute(request, response);
		}
		
		// 응답View로 이동
		if(af != null) {
			if(af.isRedirect()) {
				response.sendRedirect(af.getView());
			} else {
				request.getRequestDispatcher(af.getView()).forward(request, response);
			}
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
