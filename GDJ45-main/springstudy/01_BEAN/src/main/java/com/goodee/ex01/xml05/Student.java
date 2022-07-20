package com.goodee.ex01.xml05;

import java.util.Map;

public class Student {

	// field
	private String name;
	private Exam exam;
	private Map<String, String> pInfo;  // address, phone
	
	// getter/setter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Exam getExam() {
		return exam;
	}
	public void setExam(Exam exam) {
		this.exam = exam;
	}
	// 이클립스가 만들어 준 이름 getpInfo(), setpInfo()를 getPInfo(), setPInfo()로 변경해 줘야 <bean> 태그가 인식할 수 있다.
	public Map<String, String> getPInfo() {
		return pInfo;
	}
	public void setPInfo(Map<String, String> pInfo) {
		this.pInfo = pInfo;
	}
	
}
