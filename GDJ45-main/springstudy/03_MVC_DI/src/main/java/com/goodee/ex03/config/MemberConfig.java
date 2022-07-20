package com.goodee.ex03.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.goodee.ex03.domain.Member;

@Configuration
public class MemberConfig {
	
	// 자바로 bean을 만드는 경우에는
	// bean의 이름(id)이 qualifier 역할을 수행합니다.
	
	@Bean
	public Member member1() {  // bean의 id는 member1입니다.
		Member member = new Member();
		member.setId("admin");
		member.setPw("123456");
		return member;
	}
	
	@Bean
	public Member member2() {  // bean의 id는 member2입니다.
		Member member = new Member();
		member.setId("user");
		member.setPw("654321");
		return member;
	}
	
}
