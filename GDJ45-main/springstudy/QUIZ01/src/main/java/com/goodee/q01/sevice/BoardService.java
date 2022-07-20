package com.goodee.q01.sevice;

import java.util.List;

import com.goodee.q01.domain.BoardDTO;

public interface BoardService {
	public List<BoardDTO> findBoards();
	public Long getBoardCount();
	public BoardDTO findBoardByNo(Long no);
	public void increseHit(Long no);
	public int save(BoardDTO board);
	public int change(BoardDTO board);
	public int remove(Long no);
}
