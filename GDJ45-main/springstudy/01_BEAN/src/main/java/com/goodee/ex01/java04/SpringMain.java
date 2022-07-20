package com.goodee.ex01.java04;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringMain {

	public static void main(String[] args) {
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("java/book-context.xml");
		
		Book book1 = (Book)ctx.getBean("book1");
		System.out.println("제목 : " + book1.getTitle());
		System.out.println("저자 : " + book1.getAuthor());
		System.out.println("출판사 : " +  book1.getPublisher());

		Book book2 = (Book)ctx.getBean("book2");
		System.out.println("제목 : " + book2.getTitle());
		System.out.println("저자 : " + book2.getAuthor());
		System.out.println("출판사 : " +  book2.getPublisher());
		
		ctx.close();

	}

}
