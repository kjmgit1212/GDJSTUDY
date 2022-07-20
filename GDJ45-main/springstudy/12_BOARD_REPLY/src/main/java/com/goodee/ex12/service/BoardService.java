package com.goodee.ex12.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import com.goodee.ex12.domain.BoardDTO;

public interface BoardService {
	public void findBoards(HttpServletRequest request, Model model);
	public void findBoardByNo(HttpServletRequest request, HttpServletResponse response, Model model);
	public int save(HttpServletRequest request);
	public int change(BoardDTO board);
	public int remove(Long boardNo);
}
