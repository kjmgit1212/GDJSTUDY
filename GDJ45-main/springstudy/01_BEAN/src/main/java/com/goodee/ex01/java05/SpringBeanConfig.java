package com.goodee.ex01.java05;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBeanConfig {

	@Bean
	public Gun gun2() {
		return new Gun("AK-47", 20);  // constructor-injection
	}
	
	@Bean
	public Soldier soldier2() {
		Map<String, String> army = new HashMap<String, String>();
		army.put("name", "이기자");
		army.put("location",  "화천");
		return new Soldier("박중사", gun2(), army);  // constructor-injection
	}
	
}
