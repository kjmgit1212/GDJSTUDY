package com.goodee.ex17.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.goodee.ex17.service.FreeBoardService;
import com.goodee.ex17.service.FreeBoardServiceImpl;

@Configuration
public class ServiceConfig {

	@Bean
	public FreeBoardService freeBoardService() {
		return new FreeBoardServiceImpl();
	}
}

