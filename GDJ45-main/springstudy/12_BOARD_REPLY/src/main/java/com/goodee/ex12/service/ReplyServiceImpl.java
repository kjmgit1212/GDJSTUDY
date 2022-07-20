package com.goodee.ex12.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goodee.ex12.domain.ReplyDTO;
import com.goodee.ex12.mapper.ReplyMapper;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyMapper replyMapper;
	
	@Override
	public Map<String, Object> findReplies(Long boardNo) {
		Map<String, Object> map = new HashMap<>();
		map.put("replyCount", replyMapper.selectReplyCount(boardNo));
		map.put("replies", replyMapper.selectReplyList(boardNo));
		return map;
	}
	
	@Override
	public Map<String, Object> saveReply(HttpServletRequest request) {
		ReplyDTO reply = ReplyDTO.builder()
				.boardNo(Long.parseLong(request.getParameter("boardNo")))
				.content(request.getParameter("content"))
				.ip(request.getRemoteAddr())
				.writer(request.getParameter("writer"))
				.build();
		Map<String, Object> map = new HashMap<>();
		map.put("res", replyMapper.insertReply(reply));
		return map;
	}
	
	@Override
	public Map<String, Object> removeReply(Long replyNo) {
		Map<String, Object> map = new HashMap<>();
		map.put("res", replyMapper.deleteReply(replyNo));
		return map;
	}
	
}
