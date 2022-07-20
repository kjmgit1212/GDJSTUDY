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

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length() + 1);
		
		BoardService service = null;
		ActionForward af = null;
		
		switch(command) {
		case "addPage.do":
			af = new ActionForward("board/add.jsp", false);
			break;
		case "list.do":
			service = new ListService();
			break;
		case "detail.do":
			service = new DetailService();
			break;
		case "add.do":
			service = new AddService();
			break;
		case "modify.do":
			service = new ModifyService();
			break;
		case "remove.do":
			service = new RemoveService();
			break;
		}
		
		if(service != null) {
			af = service.execute(request, response);
		}
		
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
