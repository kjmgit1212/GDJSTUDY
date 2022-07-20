package quiz;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Quiz2B")
public class Quiz2B extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Quiz2B() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		String name = request.getParameter("name");
		// String name = URLDecoder.decode(request.getParameter("name"), "UTF-8");
		String age = request.getParameter("age");
		
		
		
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
