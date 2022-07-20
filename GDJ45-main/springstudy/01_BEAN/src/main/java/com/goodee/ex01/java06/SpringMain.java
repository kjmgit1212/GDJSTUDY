package com.goodee.ex01.java06;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class SpringMain {

	public static void main(String[] args) {
		
		AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(SpringBeanConfig.class);
		
		Customer c1 = (Customer)ctx.getBean("customer1");
		c1.info();
		
		Customer c2 = ctx.getBean("customer2", Customer.class);
		c2.info();
		
		ctx.close();

	}

}
