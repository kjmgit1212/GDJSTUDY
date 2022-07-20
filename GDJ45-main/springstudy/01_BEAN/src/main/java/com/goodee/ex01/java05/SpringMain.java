package com.goodee.ex01.java05;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringMain {

	public static void main(String[] args) {
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("java/soldier-context.xml");
		
		Soldier s1 = ctx.getBean("soldier1", Soldier.class);
		s1.info();
		
		System.out.println();
		
		Soldier s2 = ctx.getBean("soldier2", Soldier.class);
		s2.info();
		
		ctx.close();

	}

}
