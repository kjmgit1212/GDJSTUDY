package ex04;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FormServlet")
public class FormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FormServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 1. 요청(request)
		
		request.setCharacterEncoding("UTF-8");
		
		String name = request.getParameter("name");
		String strAge = request.getParameter("age");
		
		// 디폴트 처리
		// name="아무개" age=0

		// <form> 태그 요소에 name 속성이 있으면
		// 값의 입력이 없는 경우 빈 문자열("")로 처리해야 한다.
		// null로 처리하면 안 된다. (따라서 Optional도 사용할 수 없다.)
		
		if(name.isEmpty()) {  // name.equals("")
			name = "아무개";
		}
		if(strAge.isEmpty()) {
			strAge = "0";
		}
		
		int age = Integer.parseInt(strAge);
		
		
		// 2. 응답(response)
		
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		out.println("<script>");
		out.println("alert('이름 " + name + " 나이 " + age + "')");
		out.println("history.back()");
		out.println("</script>");
		
		out.flush();
		out.close();
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
