package ex06;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

@WebServlet("/ResponseJSONServlet")
public class ResponseJSONServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ResponseJSONServlet() {
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
			
			response.setContentType("application/json; charset=UTF-8");
			
			JSONObject obj = new JSONObject();
			obj.put("name", name);
			obj.put("age", age);
			
			System.out.println(obj.toString());
			
			PrintWriter out = response.getWriter();
			out.println(obj);
			
			out.flush();
			out.close();
			
		} catch(NumberFormatException e) {
			
			// 응답
			response.setContentType("text/plain; charset=UTF-8");
			
			// 예외코드
			response.setStatus(2000);
			
			// 예외메시지
			PrintWriter out = response.getWriter();
			out.println("age는 정수로 전달해주세요");
			
			out.flush();
			out.close();
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
