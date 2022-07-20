package ex06;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ResponseTextServlet")
public class ResponseTextServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ResponseTextServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
		
			// 요청
			
			request.setCharacterEncoding("UTF-8");
			
			String name = request.getParameter("name");
			String strAge = request.getParameter("age");
			int age = Integer.parseInt(strAge);
			
			
			
			// 응답
			response.setContentType("text/plain; charset=UTF-8");  // 텍스트 데이터 타입
			PrintWriter out = response.getWriter();
			out.println("이름은 " + name + "이고, 나이는 " + age + "살입니다.");
			
			out.flush();
			out.close();
			
		} catch(NumberFormatException e) {
			
			// 응답
			
			response.setContentType("text/plain; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			// 예외코드
			response.setStatus(2000);
			
			// 예외메시지
			out.println("age는 정수만 전달해주세요");
			
			out.flush();
			out.close();
			
		}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
