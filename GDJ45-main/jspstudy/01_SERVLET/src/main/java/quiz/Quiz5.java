package quiz;

import java.io.BufferedReader;
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

@WebServlet("/Quiz5")
public class Quiz5 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Quiz5() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String query = request.getParameter("query");
		
		String clientId = "XvHSzjB4Bj49XBbfspdN";
		String clientSecret = "ARhr4OmF6r";
		
		
		String text = null;
		try {
			text = URLEncoder.encode(query, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			response.setContentType("text/plain; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("검색어 인코딩 실패");
			out.flush();
			out.close();
		}
		
		
		String apiURL = "https://openapi.naver.com/v1/search/movie.json?query=" + text;
		URL url = null;
		HttpURLConnection con = null;
		try {
			url = new URL(apiURL);
			con = (HttpURLConnection)url.openConnection();
		} catch (MalformedURLException e) {
			response.setContentType("text/plain; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("API URL이 잘못되었습니다.");
			out.flush();
			out.close();
		} catch (IOException e) {
			response.setContentType("text/plain; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("연결이 실패했습니다.");
			out.flush();
			out.close();
		}
		
		
		try {
			con.setRequestMethod("GET");
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
		} catch (IOException e) {
			response.setContentType("text/plain; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("API 요청이 실패했습니다.");
			out.flush();
			out.close();
		}
		
		
		StringBuilder sb = new StringBuilder();
		try {
			int responseCode = con.getResponseCode();
			InputStreamReader streamReader = null;
			if(responseCode == HttpURLConnection.HTTP_OK) {
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
