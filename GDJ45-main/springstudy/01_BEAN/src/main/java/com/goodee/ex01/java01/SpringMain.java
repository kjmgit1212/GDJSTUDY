package com.goodee.ex01.java01;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class SpringMain {

	public static void main(String[] args) {
		
		AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(SpringBeanConfig.class);
		
		Singer singer = ctx.getBean("mySinger", Singer.class);
		singer.info();
		
		ctx.close();

	}

}
