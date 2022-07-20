package ex05;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Redirect1")
public class Redirect1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Redirect1() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/*
			redirect
			
			1. 클라이언트에게 다시 이동할 경로를 응답(response)해 준다.
			2. 클라이언트의 요청 정보(parameter)는 사라진다.
			   요청(request) 정보는 응답(response)이 오면 사라지기 때문이다.(요청은 1회용)
			3. 클라이언트가 redirect할 주소를 응답 받아서 다시 이동하기 때문에
			   주소창을 통해서 어느 곳으로 redirect했는지 알 수 있다.
		*/
		
		response.sendRedirect("/SERVLET/Redirect2");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
