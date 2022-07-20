package com.goodee.ex06.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.goodee.ex06.domain.BoardDTO;
import com.goodee.ex06.repository.BoardRepository;

public class BoardServiceImpl implements BoardService {

	
	// 서비스는 사용자의 요청을 데이터베이스로 전달하고,
	// 데이터베이스 처리 결과를 사용자에게 응답한다.
	
	
	// 서비스는 BoardConfig.java에서 BoardRepository bean을 가져와야 한다.(DI)
	@Autowired
	private BoardRepository boardRepository;
	
	
	@Override
	public List<BoardDTO> findBoards() {
		return boardRepository.selectBoards();
	}

	@Override
	public BoardDTO findBoardByNo(Long board_no) {
		return boardRepository.selectBoardByNo(board_no);
	}

	@Override
	public void save(BoardDTO board) {
		boardRepository.insertBoard(board);
	}

	@Override
	public void modify(BoardDTO board) {
		boardRepository.updateBoard(board);
	}

	@Override
	public void remove(Long board_no) {
		boardRepository.deleteBoard(board_no);
	}

}
