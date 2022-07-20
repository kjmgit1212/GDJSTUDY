package com.goodee.ex08.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.goodee.ex08.repository.BookRepository;
import com.goodee.ex08.service.BookService;
import com.goodee.ex08.service.BookServiceImpl;

@Configuration
public class BookConfig {

	@Bean
	public BookService bookService() {
		return new BookServiceImpl();
	}
	
	@Bean
	public BookRepository bookRepository() {
		return new BookRepository();
	}
	
}
