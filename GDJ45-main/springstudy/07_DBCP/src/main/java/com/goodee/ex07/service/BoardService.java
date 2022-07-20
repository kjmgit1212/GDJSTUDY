package com.goodee.ex07.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goodee.ex07.domain.BoardDTO;

// Service의 메소드 명칭은 사용자 친화적으로 작성.
// select/insert/update/delete과 같은 명칭은 사용하지 않음.

public interface BoardService {
	public List<BoardDTO> findBoards();
	public BoardDTO findBoardByNo(Long board_no);
	public void save(BoardDTO board, HttpServletRequest request, HttpServletResponse response);
	public void modify(BoardDTO board, HttpServletRequest request, HttpServletResponse response);
	public void remove(Long board_no, HttpServletRequest request, HttpServletResponse response);
}
