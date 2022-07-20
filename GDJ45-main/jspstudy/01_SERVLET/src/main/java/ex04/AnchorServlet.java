package ex04;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AnchorServlet")
public class AnchorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AnchorServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	// 1. 요청(request) 처리
    	
    	request.setCharacterEncoding("UTF-8");
    	
    	String name = request.getParameter("name");
    	String strAge = request.getParameter("age");
    	
    	// 요청 파라미터 디폴트 처리하기
    	// =전달되지 않은 파라미터의 기본값 지정하기
    	
    	// name은 "아무개", age는 0을 기본값으로 지정하기
    	if(name == null) {
    		name = "아무개";
    	}
    	if(strAge == null) {
    		strAge = "0";
    	}
    	
    	int age = Integer.parseInt(strAge);
    	
    	
    	// 2. 응답(response) 만들기
    	
    	response.setContentType("text/html; charset=UTF-8");
    	
    	PrintWriter out = response.getWriter();
    	
    	out.println("<script>");
    	out.println("alert('이름은 " + name + "이고, 나이는 " + age + "살입니다.')");
    	out.println("history.back()");
    	out.println("</script>");
    	
    	out.flush();
    	out.close();
    	
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
