package quiz;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Quiz4")
public class Quiz4 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Quiz4() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		String source = request.getParameter("source");
		String target = request.getParameter("target");
		String text = request.getParameter("text");
		
		String clientId = "XvHSzjB4Bj49XBbfspdN";
		String clientSecret = "ARhr4OmF6r";
		
		String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
		
		try {
			text = URLEncoder.encode(text, "UTF-8");
		} catch(UnsupportedEncodingException e) {
			response.setContentType("text/plain; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("인코딩 실패");
			out.flush();
			out.close();
		}
		
		URL url = null;
		HttpURLConnection con = null;
		
		try {
			url = new URL(apiURL);
			con = (HttpURLConnection)url.openConnection();
		} catch(MalformedURLException e) {
			response.setContentType("text/plain; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("API URL이 잘못되었습니다.");
			out.flush();
			out.close();
		} catch(IOException e) {
			response.setContentType("text/plain; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("연결이 실패했습니다.");
			out.flush();
			out.close();
		}
		
		// api 요청
		try {
			String postParams = "source=" + source + "&target=" + target + "&text=" + text;
			con.setRequestMethod("POST");
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.write(postParams.getBytes());
			wr.flush();
			if(wr != null)
				wr.close();
		} catch (IOException e) {
			response.setContentType("text/plain; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("API 요청이 실패했습니다.");
			out.flush();
			out.close();
		}
		
		// api 응답
		StringBuilder sb = new StringBuilder();
		try {
			InputStreamReader streamReader = null;
			if(con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				streamReader = new InputStreamReader(con.getInputStream());
			} else {
				streamReader = new InputStreamReader(con.getErrorStream());
			}
			BufferedReader br = new BufferedReader(streamReader);
			String line = null;
			while((line = br.readLine()) != null) {
				sb.append(line);
			}
			if(br != null)
				br.close();
		} catch (IOException e) {
			response.setContentType("text/plain; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("API 응답을 읽는데 실패했습니다.");
			out.flush();
			out.close();
		}
		
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(sb.toString());
		out.flush();
		out.close();
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
