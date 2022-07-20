package ex05;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Forward1")
public class Forward1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Forward1() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/*
			forward
			
			1. 클라이언트의 요청(request)을 서버 내부 다른 주소로 전달한다.
			2. 클라이언트의 요청 정보(parameter)가 그대로 유지된다.
			3. 클라이언트는 서버 내부 이동 경로를 알 수 없기 때문에
			   주소창을 통해서 어느 곳으로 forward했는지 알 수 없다.
		*/
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Forward2");  // forward는 서버 내부 주소만 작성해야 한다.(컨텍스트 패스는 작성하면 안 된다.)
		dispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
