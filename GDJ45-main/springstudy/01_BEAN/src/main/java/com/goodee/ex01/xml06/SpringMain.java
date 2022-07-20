package com.goodee.ex01.xml06;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringMain {

	public static void main(String[] args) {
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("xml/context06.xml");
		
		GymMember member = ctx.getBean("gymMember", GymMember.class);
		member.info();  // 이름, 등록과정, 키, 몸무게, BMI, 건강상태 출력하기
		
		ctx.close();
		
	}

}
