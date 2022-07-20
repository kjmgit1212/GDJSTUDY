package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchService implements OpenAPIService {

	private void error(HttpServletResponse response, String msg) throws IOException {
		response.setContentType("text/plain; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(msg);
		out.flush();
		out.close();
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// 요청 parameter
		String query = request.getParameter("query");
		String display = request.getParameter("display");
		String sort = request.getParameter("sort");
		
		// 검색어 인코딩 UTF-8
		try {
			query = URLEncoder.encode(query, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			error(response, "검색어 인코딩 실패");
		}
		
		// API 접속
		String apiURL = "https://openapi.naver.com/v1/search/shop.json?query=" + query + "&display=" + display + "&sort=" + sort;
		URL url = null;
		HttpURLConnection con = null;
		try {
			url = new URL(apiURL);
			con = (HttpURLConnection)url.openConnection();
		} catch(MalformedURLException e) {
			error(response, "잘못된 형식의 apiURL");
		} catch(IOException e) {
			error(response, "apiURL 연결 실패");
		}
		
		// 요청 method + header 
		try {
			con.setRequestMethod("GET");  // GET 대문자로 작성
			con.setRequestProperty("X-Naver-Client-Id", "XvHSzjB4Bj49XBbfspdN");
			con.setRequestProperty("X-Naver-Client-Secret", "ARhr4OmF6r");
		} catch (Exception e) {
			error(response, "API 요청 실패");
		}
		
		// 응답 받을 스트림(성공하면 정상 스트림, 실패하면 에러 스트림)
		BufferedReader br = null;
		int responseCode = con.getResponseCode();
		if(responseCode == 200) {  // 200 == HttpURLConnection.HTTP_OK
			br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		} else {
			br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		}
		
		// 응답(네이버에서 알려준 검색결과)
		StringBuilder sb = new StringBuilder();
		try {
			String line = null;
			while((line = br.readLine()) != null) {
				sb.append(line);
			}
			br.close();
		} catch (IOException e) {
			error(response, "API 응답 실패");
		}
		
		// API 연결 종료
		con.disconnect();
		
		// 응답(네이버에서 알려준 검색결과)을 search.jsp로 보내기
		// 응답은 sb에 저장되어 있다.
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(sb.toString());
		out.flush();
		out.close();
		
	}
	
}
