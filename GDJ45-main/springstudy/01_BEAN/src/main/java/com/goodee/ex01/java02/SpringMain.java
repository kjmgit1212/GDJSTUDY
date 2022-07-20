package com.goodee.ex01.java02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class SpringMain {

	public static void main(String[] args) {
		
		AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(SpringBeanConfig.class);
		
		Student student = ctx.getBean("stu", Student.class);
		System.out.println("성적 : " + student.getScores().toString());
		System.out.println("수상 : " + student.getAwards().toString());
		System.out.println("정보 : " + student.getHome().toString());
		
		ctx.close();

	}

}
