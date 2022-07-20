package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import service.AddService;
import service.BoardService;
import service.DetailService;
import service.ListService;
import service.ModifyService;
import service.RemoveService;

@WebServlet("*.do")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public BoardController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 요청/응답 인코딩
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// URLMapping
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length() + 1);
		
		// BoardService 인스턴스
		BoardService service = null;
		
		// ActionForward 인스턴스
		ActionForward af = null;
		
		// Service 선택
		switch(command) {
		case "insertPage.do":
			af = new ActionForward("board/insert.jsp", false);
			break;
		case "updatePage.do":
			af = new ActionForward("board/update.jsp", false);
			break;
		
		case "list.do":
			service = new ListService();
			break;
		case "insert.do":
			service = new AddService();
			break;
		case "detail.do":
			service = new DetailService();
			break;
		case "modify.do":
			service = new ModifyService();
			break;
		case "remove.do":
			service = new RemoveService();
			break;
		}
		
		// service 실행
		if(service != null) {
			af = service.execute(request, response);
		}
		
		// 어디로 어떻게?
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
