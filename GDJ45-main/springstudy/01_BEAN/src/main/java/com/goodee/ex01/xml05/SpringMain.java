package com.goodee.ex01.xml05;

import java.util.Map;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringMain {

	public static void main(String[] args) {
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("xml/context05.xml");
		Student student = ctx.getBean("stu", Student.class);
		
		// getter를 이용한 데이터 점검
		System.out.println("name : " + student.getName());
		System.out.println("scores : " + student.getExam().getScores().toString());
		System.out.println("average : " + student.getExam().getAverage());
		System.out.println("grade : " + student.getExam().getGrade());
		for(Map.Entry<String, String> entry : student.getPInfo().entrySet()) {
			System.out.println("pInfo's " + entry.getKey() + " : " + entry.getValue());
		}
		
		ctx.close();
		
	}

}
