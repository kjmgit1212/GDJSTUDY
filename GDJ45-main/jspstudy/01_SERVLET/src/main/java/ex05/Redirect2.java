package ex05;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Redirect2")
public class Redirect2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Redirect2() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 1. 요청 확인
		request.setCharacterEncoding("UTF-8");
		
		String name = request.getParameter("name");
		String strAge = request.getParameter("age");
		
		
		
		// 2. 응답
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		out.println("<h1>이름 " + name + "</h1>");
		out.println("<h1>나이 " + strAge + "</h1>");
		out.println("<input type=\"button\" value=\"뒤로가기\" onclick=\"history.back()\">");
		
		out.flush();
		out.close();
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
