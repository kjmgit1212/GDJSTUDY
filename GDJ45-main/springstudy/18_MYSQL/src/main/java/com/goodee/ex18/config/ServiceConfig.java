package com.goodee.ex18.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.goodee.ex18.service.NoticeService;
import com.goodee.ex18.service.NoticeServiceImpl;

@Configuration
public class ServiceConfig {
	
	@Bean
	public NoticeService noticeService() {
		return new NoticeServiceImpl();
	}
	
}
