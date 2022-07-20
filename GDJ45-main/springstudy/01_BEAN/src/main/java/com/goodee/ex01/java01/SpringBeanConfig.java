package com.goodee.ex01.java01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
	@Configuration
	안녕. 난 bean을 만드는 java class 파일이야.
	spring bean configuration file과 같은 일을 하지.
*/

@Configuration
public class SpringBeanConfig {
	
	// 메소드 1개 = bean 1개
	
	// bean을 만드는 메소드는 모두 @Bean이 필요해요.
	
	
	/* Song */
	
	// 1. 반환타입 : Song    <bean class="Song">
	// 2. 메소드명 : mySong  <bean id="mySong">

	@Bean  // 나는 bean을 만듭니다.
	public Song mySong() {
		
		// Song 객체를 만들어서 반환하기
		// setter injection을 하든 constructor injection을 하든 자유~
		
		Song res = new Song();
		res.setTitle("hello");  // setter injection <property name="title" value="hello" />
		res.setGenre("balad");  // setter injection <property name="genre" value="balad" />
		
		return res;
		
	}
	
	@Bean
	public Singer mySinger() {  // <bean class="Singer" id="mySinger">
		
		// Singer 객체를 만들어서 반환하기
		// setter injection을 하든 constructor injection을 하든 자유~
		
		return new Singer("adele", mySong());  // constructor injection
		
		// <bean class="Singer" id="mySinger">
		//   <constructor-arg value="adele" />
		//   <constructor-arg ref="mySong" />
		// </bean>
		
	}

}
