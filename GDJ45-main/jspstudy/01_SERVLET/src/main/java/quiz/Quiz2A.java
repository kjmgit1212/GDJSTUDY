package quiz;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Quiz2A")
public class Quiz2A extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Quiz2A() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		
		
		
		response.sendRedirect("/SERVLET/Quiz2B?name=" + URLEncoder.encode(name, "UTF-8") + "&age=" + age);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
