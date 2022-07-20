package com.goodee.ex01.java06;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

// java/customer-context.xml에 만들어져 있는 bean을 가져오세요
@ImportResource("java/customer-context.xml")

@Configuration
public class SpringBeanConfig {

	@Bean
	public BankAccount bank1() {
		// default constructor + setter injection
		BankAccount bank = new BankAccount();
		bank.setAccNo("010-1234-5678");
		bank.setBalance(50000L);
		return bank;
	}
	
	@Bean
	public Customer customer1() {
		// default constructor + setter injection
		Customer customer = new Customer();
		customer.setName("영숙");
		Map<String, String> info = new HashMap<String, String>();
		info.put("tel", "010-1234-5678");
		info.put("grade", "vip");
		customer.setInfo(info);
		customer.setBankAccount(bank1());
		return customer;
	}	
	
}
