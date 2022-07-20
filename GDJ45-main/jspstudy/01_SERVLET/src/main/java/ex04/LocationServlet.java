package ex04;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LocationServlet")
public class LocationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LocationServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 1. 요청
		
		request.setCharacterEncoding("UTF-8");

		String name = request.getParameter("name");
		String strAge = request.getParameter("age");
		
		// 디폴트 처리하기
		// name은 "아무개", age는 0
		
		// Optional 클래스
		// 1. null 처리를 위한 wrapper 클래스
		// 2. jdk 1.8 이상 지원
		// 3. 주요 메소드
		//    1) 인수가 null이 아닌 경우만 처리
		//        (1) 감싸기   : Optional.of(인수)
		//        (2) 가져오기 : get()
		//    2) 인수가 null인 경우도 처리 (우리가 사용할 내용)
		//        (1) 감싸기   : Optional.ofNullable(인수)
		//        (2) 가져오기 : orElse()
		
		Optional<String> optName = Optional.ofNullable(name);
		String resName = optName.orElse("아무개");  // null: 아무개, not null: name
		
		Optional<String> optAge = Optional.ofNullable(strAge);
		String resAge = optAge.orElse("0");
		
		
		// 2. 응답
		
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		out.println("<h1>이름 " + resName + "</h1>");
		out.println("<h1>나이 " + resAge + "</h1>");
		out.println("<input type=\"button\" value=\"돌아가기\" onclick=\"history.back()\">");
		
		out.flush();
		out.close();
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
