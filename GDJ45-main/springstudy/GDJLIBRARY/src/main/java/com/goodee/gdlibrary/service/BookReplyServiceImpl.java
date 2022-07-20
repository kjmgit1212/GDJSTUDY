package com.goodee.gdlibrary.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.goodee.gdlibrary.domain.BookReplyDTO;
import com.goodee.gdlibrary.domain.MemberDTO;
import com.goodee.gdlibrary.mapper.BookReplyMapper;
import com.goodee.gdlibrary.util.PageUtils1;

public class BookReplyServiceImpl implements BookReplyService {

		@Autowired
		private BookReplyMapper bookReplyMapper;
	
		@Override
		// 감상평 등록
		public Map<String, Object> addReply(HttpServletRequest request) {

		MemberDTO loginMember = (MemberDTO) request.getSession().getAttribute("loginMember");

	    String memberId = loginMember.getMemberId();
	    Long bookNo = Long.parseLong(request.getParameter("bookNo"));
	    String bookReplyContent = request.getParameter("bookReplyContent");
	    Integer bookRating = Integer.parseInt(request.getParameter("bookRating")); 
	    

	    BookReplyDTO reply = BookReplyDTO.builder()
	    					.bookNo(bookNo)
	    		  			.bookReplyContent(bookReplyContent)
	    		  			.bookRating(bookRating)
	    		  			.memberId(memberId).build();
	    int resRent = 0;
	    resRent += bookReplyMapper.selectRentCheck(reply);
	    resRent += bookReplyMapper.selectReturnCheck(reply);
	    resRent += bookReplyMapper.selectOverdueCheck(reply);
	    
	    Map<String, Object> map = new HashMap<>();
	    if(resRent == 0) {	
	    	map.put("res", null);
	    	return map;
	    }else {
	    	
	    	map.put("res", bookReplyMapper.insertReply(reply));
	    	return map;
	    	
	    	}
	    	
		}
	
		
		@Override
		// 등록된 감상평목록 보기
		public Map<String, Object> bookReplyList(HttpServletRequest request) {
		Long bookNo = Long.parseLong(request.getParameter("bookNo"));
		int page = Integer.parseInt(request.getParameter("page"));
		

		
		Integer totalRecord = bookReplyMapper.selectReplyCount(bookNo);
		PageUtils1 p = new PageUtils1();
		p.setPageEntity(totalRecord, page);
		

		Map<String, Object> map = new HashMap<>();
		map.put("beginRecord", p.getBeginRecord()-1);
		map.put("recordPerPage", p.getRecordPerPage());
		map.put("bookNo", bookNo);

		
		List<BookReplyDTO> replies = bookReplyMapper.selectReplyList(map);
		
		Map<String, Object> resMap = new HashMap<>();
		resMap.put("replies", replies);

		resMap.put("p", p);
		resMap.put("replyCount", bookReplyMapper.selectReplyCount(bookNo));

		resMap.put("replyRatingAverage", bookReplyMapper.selectReplyRatingAverage(bookNo));

		return resMap;
		}
		
  }