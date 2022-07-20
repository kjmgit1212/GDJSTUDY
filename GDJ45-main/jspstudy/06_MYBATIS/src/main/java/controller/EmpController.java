package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import service.AddService;
import service.DetailService;
import service.EmpService;
import service.ListService;
import service.ModifyService;
import service.RemoveService;

@WebServlet("*.do")
public class EmpController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public EmpController() {
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
		
		// EmpService 인스턴스
		EmpService service = null;
		
		// ActionForward 인스턴스
		ActionForward af = null;
		
		// service 선택
		switch(command) {
		// 단순 이동 forward
		case "insertPage.do":
			af = new ActionForward("emp/insert.jsp", false);
			break;
		case "updatePage.do":
			af = new ActionForward("emp/update.jsp", false);
			break;
		
		// 서비스 실행
		case "list.do":
			service = new ListService();
			break;
		case "detail.do":
			service = new DetailService();
			break;
		case "insert.do":
			service = new AddService();
			break;
		case "update.do":
			service = new ModifyService();
			break;
		case "delete.do":
			service = new RemoveService();
			break;
		}
		
		// service 실행(execute() 메소드 실행 -> IOException 발생)
		try {
			if(service != null) {
				af = service.execute(request, response);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// 이디로 어떻게 이동할 것인가?
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
