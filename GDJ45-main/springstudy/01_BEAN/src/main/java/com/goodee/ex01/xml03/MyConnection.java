package com.goodee.ex01.xml03;

import java.sql.Connection;
import java.sql.DriverManager;

public class MyConnection {

	// field
	private String driverClassName;
	private String url;
	private String username;
	private String password;
	
	// getter/setter
	public String getDriverClassName() {
		return driverClassName;
	}
	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	// getConnnection() 메소드
	public Connection getConnection() {
		Connection con = null;
		try {
			Class.forName(driverClassName);
			con = DriverManager.getConnection(url, username, password);
			System.out.println("Connection이 생성되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
}
