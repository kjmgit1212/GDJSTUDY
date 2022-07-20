package com.goodee.q01.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.goodee.q01.repository.BoardRepository;
import com.goodee.q01.sevice.BoardService;
import com.goodee.q01.sevice.BoardServiceImpl;

@Configuration
public class BoardConfig {

	@Bean
	public BoardService boardService() {
		return new BoardServiceImpl();
	}
	
	@Bean
	public BoardRepository boardRepository() {
		return new BoardRepository();
	}
	
}
