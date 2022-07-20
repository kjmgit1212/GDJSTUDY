package quiz;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Quiz3B")
public class Quiz3B extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Quiz3B() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 요청
		
		request.setCharacterEncoding("UTF-8");
		
		String from = request.getParameter("from");
		if(from.isEmpty()) {
			from = "민경태";
		}
		String to = request.getParameter("to");
		if(to.isEmpty()) {
			to = "배수지";
		}
		String content = request.getParameter("content");
		
		
		
		// 파일 만들기
		
		// 저장할 디렉터리
		File dir = new File("C:\\GDJ45\\jspstudy\\01_SERVLET\\storage");
		if(dir.exists() == false) {
			dir.mkdirs();
		}
		
		// 저장할 파일명
		String today = new Date(System.currentTimeMillis()) + "";
		String filename = today + "_" + from + ".txt";
		
		// 파일 인스턴스 생성
		File file = new File(dir, filename);
		
		// 출력 스트림 생성(문자 기반)
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		
		// 파일의 내용 생성
		bw.write("작성일 : " + today);
		bw.newLine();
		bw.write("보내는사람 : " + from);
		bw.newLine();
		bw.write("받는사람 : " + to);
		bw.newLine();
		bw.newLine();
		bw.write(content);
		
		if(bw != null)
			bw.close();
	
		
		
		// 응답
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('" + file.getName() + " 파일이 생성되었습니다.')");
		out.println("history.back()");
		out.println("</script>");
		
		out.flush();
		out.close();
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
