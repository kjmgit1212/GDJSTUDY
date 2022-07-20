package com.goodee.ex01.java03;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppContext {

	@Bean(name="calc")     // <bean id="calc">
	public Calculator aaa() {
		return new Calculator();
	}
	
	@Bean(name="gugudan")  // <bean id="gugudan">
	public Gugudan bbb() {
		return new Gugudan(aaa(), 3, 5);
	}
	
}
