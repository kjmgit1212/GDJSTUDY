package com.goodee.gdlibrary.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.goodee.gdlibrary.domain.BookDTO;
import com.goodee.gdlibrary.mapper.BookMapper;
import com.goodee.gdlibrary.util.PageUtils1;
@PropertySource(value={"classpath:secret/secret.properties"})
@Service
public class BookServiceImpl implements BookService {
	@Value(value="${book.api.key}") private String bookApiKey;
		@Autowired
		private BookMapper bookMapper;
	
		@Transactional
		@Override
		// api정보 받아오기
		public void getBooksInfo(HttpServletRequest request) {

		// API URL with Parameter
		String key = bookApiKey;
		int startRowNumApi = 1;
		int endRowNemApi = 30;
		Long query = Long.parseLong(request.getParameter("query"));

		String apiURL = "https://nl.go.kr/NL/search/openApi/saseoApi.do";
		apiURL += "?key=" + key;
		apiURL += "&startRowNumApi=" + startRowNumApi;
		apiURL += "&endRowNemApi=" + endRowNemApi;
		apiURL += "&drCode=" + query;
		
		// API URL Connection
		URL url = null;
		HttpURLConnection con = null;
		try {
			url = new URL(apiURL);
			con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");  // 반드시 "GET" 대문자로 지정
		} catch (MalformedURLException e) {
			e.printStackTrace();  // 잘못된 apiURL
		} catch (IOException e) {
			e.printStackTrace();  // apiURL 연결 실패
		}
		
		// API 응답
		StringBuilder sb = new StringBuilder();
		try(BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
			String line = null;
			while((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch(IOException e) {
			e.printStackTrace();  // API 응답 실패
		}
		JSONObject books = XML.toJSONObject(sb.toString());
		JSONObject items = books.getJSONObject("channel");
		JSONArray item = items.getJSONArray("list");
		for(int i = 0; i < item.length(); i++) {
			JSONObject result = (JSONObject)item.get(i);
			JSONObject b = (JSONObject)result.get("item");
			BookDTO book = new BookDTO();	
			book.setBookIsbn(b.getString("recomcallno"));
			book.setBookTitle(b.getString("recomtitle"));
			book.setBookAuthor(b.getString("recomauthor"));
			book.setBookPublisher(b.getString("recompublisher"));
			book.setBookPubdateTime(b.getString("regdate"));
			book.setBookDescription(b.getString("recomcontens"));
			book.setBookImage(b.getString("recomfilepath"));
			book.setBookType(b.getString("drCodeName"));
			bookMapper.insertBook(book);
			}
		
		}
	
		
		@Override
		// 책목록 출력
		public Map<String, Object> bookList(int page) {
		
		// page, totalRecord를 이용해서 페이징
		Integer totalRecord = bookMapper.selectBookCount();
		PageUtils1 p = new PageUtils1();
		p.setPageEntity(totalRecord, page);
		
		// 목록은 beginRecord ~ endRecord 사이값을 가져온다.
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("beginRecord", p.getBeginRecord()-1);
		m.put("recordPerPage", p.getRecordPerPage());
		
		// 목록과 paging 정보를 반환
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("books", bookMapper.selectBookList(m));
		map.put("p", p);
	
		return map;
		
		}
	
		@Override
		// 책검색하기
		public Map<String, Object> searchBook(HttpServletRequest request) {

		String column = request.getParameter("column");
		String query = request.getParameter("query");
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1"));

		Map<String, Object> map = new HashMap<>();
		map.put("query", query);
		map.put("column", column);
		
		Integer totalRecord = bookMapper.searchBookCount(map);
		PageUtils1 p = new PageUtils1();
		p.setPageEntity(totalRecord, page);
		
		map.put("beginRecord", p.getBeginRecord()-1);
		map.put("recordPerPage", p.getRecordPerPage());
		
		
		List<BookDTO> books = bookMapper.searchBook(map);
		Map<String, Object> resMap = new HashMap<>();
		resMap.put("books", books);
		resMap.put("p", p);
		
		return resMap;
		
		}
	
	
	
		@Override
		// 책 상세보기
		public void detailBook(HttpServletRequest request, Model model) {
		Long bookNo = Long.parseLong(request.getParameter("bookNo"));
		model.addAttribute("book", bookMapper.detailBook(bookNo));
		
		}
	

		@Override
		// 평점순 추천도서
		public Map<String, Object> recomBook(Model model) {
		
		List<BookDTO> list = bookMapper.recomBook();
		
		Map<String, Object> map = new HashMap<>();
		map.put("recom", list);
		
		model.addAttribute("recom", map);
		

		return map;
		}
	

}	
	
