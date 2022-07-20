package com.goodee.ex01.xml04;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringMain {

	public static void main(String[] args) {
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("xml/context04.xml");
		
		CollectionClass c = ctx.getBean("collection", CollectionClass.class);
		c.info();
		
		ctx.close();

	}

}
