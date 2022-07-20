package com.goodee.ex11.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.goodee.ex11.service.EmployeeService;
import com.goodee.ex11.service.EmployeeServiceImpl;

@Configuration
public class ServiceConfig {

	@Bean
	public EmployeeService employeeService() {
		return new EmployeeServiceImpl();
	}
	
}
