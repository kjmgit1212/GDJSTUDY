package com.goodee.ex01.xml02;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringMain {

	public static void main(String[] args) {
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("xml/context02.xml");  // classpath: 생략
		
		Car car = ctx.getBean("bmw_x7", Car.class);
		car.info();  // 모델, 가격, 엔진 정보 출력하기
		
		ctx.close();

	}

}
