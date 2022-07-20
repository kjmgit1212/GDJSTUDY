package com.goodee.q01.sevice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.goodee.q01.domain.BoardDTO;
import com.goodee.q01.repository.BoardRepository;

public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	@Override
	public List<BoardDTO> findBoards() {
		return boardRepository.selectBoards();
	}

	@Override
	public Long getBoardCount() {
		return boardRepository.selectBoardCount();
	}
	
	@Override
	public BoardDTO findBoardByNo(Long no) {
		return boardRepository.selectBoardByNo(no);
	}

	@Override
	public void increseHit(Long no) {
		boardRepository.updateHit(no);
	}
	
	@Override
	public int save(BoardDTO board) {
		return boardRepository.insertBoard(board);
	}

	@Override
	public int change(BoardDTO board) {
		return boardRepository.updateBoard(board);
	}

	@Override
	public int remove(Long no) {
		return boardRepository.deleteBoard(no);
	}

}
