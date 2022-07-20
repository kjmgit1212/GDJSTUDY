package ex04;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FormServlet3")
public class FormServlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FormServlet3() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 1. 요청(request)
		
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		// radio는 하나만 선택할 수 있으므로 하나의 값만 온다.
		String gender = request.getParameter("gender");
		
		// checkbox는 여러 개를 선택할 수 있으므로 여러 개의 값이 온다.
		String[] hobbies = request.getParameterValues("hobbies");
		
		// select에 multiple 속성이 있으면 여러 개를 선택할 수 있으므로 여러 개의 값이 온다.
		String[] cities = request.getParameterValues("cities");
		
		// select에 multiple 속성이 없으면 하나만 선택할 수 있으므로 하나의 값만 온다.
		String mobile = request.getParameter("mobile");
		String tel1 = request.getParameter("tel1");
		String tel2 = request.getParameter("tel2");
		
		String email = request.getParameter("email");
		String domain = request.getParameter("domain");
		
		String info = request.getParameter("info");

		
		
		// 2. 응답(response)
		
		response.setContentType("text/html; charset=UTF-8");
		// response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		out.println("<h3>아이디 " + id + "</h3>");
		
		out.println("<h3>비밀번호 " + pw + "</h3>");
		
		out.println("<h3>성별 " + gender + "</h3>");
		
		out.println("<h3>취미</h3>");
		out.println("<ul>");
		for(int i = 0; i < hobbies.length; i++) {
			out.println("<li>" + hobbies[i] + "</li>");
		}
		out.println("</ul>");
		
		out.println("<h3>도시" + Arrays.toString(cities) + "</h3>");
		
		String phone = mobile + "-" + tel1 + "-" + tel2;
		out.println("<h3>전화 " + phone + "</h3>");
		
		email += "@" + domain;
		out.println("<h3>이메일 " + email + "</h3>");
		
		out.println("<h3>정보</h3>");
		out.println("<pre>" + info + "</pre>");
		
		out.flush();
		out.close();
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
