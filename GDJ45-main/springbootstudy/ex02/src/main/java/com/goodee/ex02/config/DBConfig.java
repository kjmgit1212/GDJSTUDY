package com.goodee.ex02.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@ComponentScan(basePackages={"com.goodee.ex02.batch"})
@EnableScheduling

@MapperScan(basePackages={"com.goodee.ex02.mapper"})
@PropertySource(value={"classpath:application.properties"})
@EnableTransactionManagement
@Configuration
public class DBConfig {

	@Value("${spring.datasource.driver-class-name}") private String driverClassName;
	@Value("${spring.datasource.url}") private String jdbcUrl;
	@Value("${spring.datasource.username}") private String username;
	@Value("${spring.datasource.password}") private String password;
	@Value("${mybatis.config-location}") private String configLocation;
	@Value("${mybatis.mapper-locations}") private String mapperLocations;
	
	@Bean
	public HikariConfig hikariConfig() {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName(driverClassName);
		hikariConfig.setJdbcUrl(jdbcUrl);
		hikariConfig.setUsername(username);
		hikariConfig.setPassword(password);
		return hikariConfig;
	}
	
	@Bean(destroyMethod="close")
	public HikariDataSource dataSource() {
		return new HikariDataSource(hikariConfig());
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource());
		sqlSessionFactoryBean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource(configLocation));
		sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));
		return sqlSessionFactoryBean.getObject();
	}
	
	@Bean
	public SqlSessionTemplate sqlSessionTemplate() throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory());
	}
	
	@Bean
	public TransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
	
}
