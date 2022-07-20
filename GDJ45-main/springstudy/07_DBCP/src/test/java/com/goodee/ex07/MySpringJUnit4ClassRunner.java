package com.goodee.ex07;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.junit.runners.model.InitializationError;
import org.springframework.jndi.JndiTemplate;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

public class MySpringJUnit4ClassRunner extends SpringJUnit4ClassRunner {

	// SpringJUnit4ClassRunner 기능을 그대로 사용하고,
	// 추가로 jndi() 메소드를 사용합니다.
	public MySpringJUnit4ClassRunner(Class<?> clazz) throws InitializationError {
		super(clazz);
		jndi();
	}
	
	private void jndi() {
		try {
			SimpleNamingContextBuilder builder = new SimpleNamingContextBuilder();
        	builder.activate();
        	
        	// JNDI 방식으로 "java:comp/env/jdbc/oracle" 리소스를 요청하면,
        	// context.xml을 읽는 대신 dataSource를 사용하십시오.
			JndiTemplate template = new JndiTemplate();
			BasicDataSource dataSource = new BasicDataSource();
			dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
			dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
			dataSource.setUsername("scott");
			dataSource.setPassword("1111");
			template.bind("java:comp/env/jdbc/oracle", dataSource);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
