package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ActionForward;
import model.Lotto;
import model.MyService;
import model.Now;
import model.Today;


// suffix값이 .do인 모든 URLMapping을 처리하시오.
@WebServlet("*.do")

public class MyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public MyController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 요청과 응답의 인코딩 처리
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		//  /MVC/today.do   /MVC/now.do  구분하는 방법
		String requestURI = request.getRequestURI();                      //   /MVC/today.do
		String contextPath = request.getContextPath();                    //   /MVC
		String command = requestURI.substring(contextPath.length() + 1);  //   today.do
		
		// 모든 model은 MyService 인터페이스를 구현하는 클래스이므로,
		// MyService 타입의 인스턴스이다.
		MyService service = null;
		
		// ActionForward 인스턴스
		ActionForward af = null;
		
		switch(command) {
		// model이 사용되는 경우
		case "today.do":
			service = new Today();
			break;
		case "now.do":
			service = new Now();
			break;
		case "lotto.do":
			service = new Lotto();
			break;
		// model이 사용되지 않는 단순 이동
		case "input.do":
			af = new ActionForward();
			af.setRedirect(false);
			af.setView("views/input.jsp");
			break;
		}
		
		// model의 실행(execute() 메소드의 호출)
		if(service != null) {
			af = service.execute(request, response);
		}

		// model이 반환한 어디로 어떻게 정보(ActionForward)를 이용해서 이동
		// af가 null인 경우가 있다. (모델에서 직접 response를 이용해 응답한 경우, ajax 처리)
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
