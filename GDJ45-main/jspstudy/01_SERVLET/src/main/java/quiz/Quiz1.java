package quiz;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Quiz1")
public class Quiz1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Quiz1() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 1. 요청
		
		request.setCharacterEncoding("UTF-8");
		
		String command = request.getParameter("command");
		
		String res = null;
		Calendar cal = Calendar.getInstance();
		switch(command) {
		case "date":
			res = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DATE);
			break;
		case "time":
			res = cal.get(Calendar.HOUR) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND);
			break;
		}
		
		
		
		// 2. 응답
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('" + res + "')");
		out.println("history.back()");
		out.println("</script>");
		
		out.flush();
		out.close();
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
