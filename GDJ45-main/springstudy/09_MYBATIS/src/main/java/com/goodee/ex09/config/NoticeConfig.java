package com.goodee.ex09.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.goodee.ex09.repository.NoticeRepository;
import com.goodee.ex09.service.NoticeService;
import com.goodee.ex09.service.NoticeServiceImpl;

@Configuration
public class NoticeConfig {

	@Bean
	public NoticeService noticeService() {
		return new NoticeServiceImpl();
	}
	
	@Bean
	public NoticeRepository noticeRepository() {
		return new NoticeRepository();
	}
	
}
