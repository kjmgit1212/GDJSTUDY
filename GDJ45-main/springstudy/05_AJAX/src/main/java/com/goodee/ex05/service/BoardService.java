package com.goodee.ex05.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.goodee.ex05.domain.BoardDTO;

public interface BoardService {
	public BoardDTO detail1(HttpServletRequest request);
	public BoardDTO detail2(String title, Long hit);
	public Map<String, Object> detail3(BoardDTO board);
	public BoardDTO detail4(Map<String, Object> map);
}
