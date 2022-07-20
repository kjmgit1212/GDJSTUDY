package com.goodee.ex10.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.goodee.ex10.service.NoticeService;
import com.goodee.ex10.service.NoticeServiceImpl;

@Configuration
public class NoticeConfig {

	@Bean
	public NoticeService noticeService() {
		return new NoticeServiceImpl();
	}
	
}
