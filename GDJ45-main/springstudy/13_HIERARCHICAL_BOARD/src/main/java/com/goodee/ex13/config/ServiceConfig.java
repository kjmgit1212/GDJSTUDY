package com.goodee.ex13.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.goodee.ex13.service.FreeBoardService;
import com.goodee.ex13.service.FreeBoardServiceImpl;

@Configuration
public class ServiceConfig {

	@Bean
	public FreeBoardService freeBoardService() {
		return new FreeBoardServiceImpl();
	}
}

