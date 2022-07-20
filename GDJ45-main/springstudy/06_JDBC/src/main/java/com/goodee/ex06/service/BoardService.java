package com.goodee.ex06.service;

import java.util.List;

import com.goodee.ex06.domain.BoardDTO;

// Service의 메소드 명칭은 사용자 친화적으로 작성.
// select/insert/update/delete과 같은 명칭은 사용하지 않음.

public interface BoardService {
	public List<BoardDTO> findBoards();
	public BoardDTO findBoardByNo(Long board_no);
	public void save(BoardDTO board);
	public void modify(BoardDTO board);
	public void remove(Long board_no);
}
