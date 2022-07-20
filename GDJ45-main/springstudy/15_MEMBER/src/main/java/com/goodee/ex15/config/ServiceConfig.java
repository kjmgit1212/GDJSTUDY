package com.goodee.ex15.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.goodee.ex15.service.MemberService;
import com.goodee.ex15.service.MemberServiceImpl;

@Configuration
public class ServiceConfig {

	@Bean
	public MemberService memberService() {
		return new MemberServiceImpl();
	}
	
}
