package com.goodee.ex17.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface FreeBoardService {
	public void findFreeBoards(HttpServletRequest request, Model model);
	public int saveFreeBoard(HttpServletRequest request);
	public int saveReply(HttpServletRequest request);
	public int removeFreeBoard(Long freeBoardNo);
}
