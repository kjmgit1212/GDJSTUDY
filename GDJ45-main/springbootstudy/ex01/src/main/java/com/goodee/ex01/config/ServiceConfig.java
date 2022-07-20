package com.goodee.ex01.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.goodee.ex01.service.BbsService;
import com.goodee.ex01.service.BbsServiceImpl;

@Configuration
public class ServiceConfig {

	@Bean
	public BbsService bbsService() {
		return new BbsServiceImpl();
	}
	
}
