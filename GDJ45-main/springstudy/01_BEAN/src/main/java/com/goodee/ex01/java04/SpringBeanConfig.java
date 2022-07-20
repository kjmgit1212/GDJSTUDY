package com.goodee.ex01.java04;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBeanConfig {

	@Bean(name="publisher2")
	public Publisher a() {
		Publisher publisher = new Publisher();
		publisher.setName("우리출판사");
		publisher.setTel("02-555-7777");
		return publisher;
	}
	
	@Bean(name="book2")
	public Book b() {
		Book book = new Book();
		book.setTitle("소나기");
		book.setAuthor("황순원");
		book.setPublisher(a());
		return book;
	}
	
}
