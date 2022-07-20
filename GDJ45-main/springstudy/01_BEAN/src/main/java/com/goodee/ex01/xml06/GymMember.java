package com.goodee.ex01.xml06;

import java.util.Set;

public class GymMember {

	// field
	private String name;         // 이름
	private Set<String> course;  // 등록과정(헬스, 스피닝, 필라테스 등)
	private double height;
	private double weight;
	private BMICalculator bmiCalculator;
	
	// getter/setter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<String> getCourse() {
		return course;
	}
	public void setCourse(Set<String> course) {
		this.course = course;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public BMICalculator getBmiCalculator() {
		return bmiCalculator;
	}
	public void setBmiCalculator(BMICalculator bmiCalculator) {
		this.bmiCalculator = bmiCalculator;
	}
	// info() 메소드
	public void info() {
		System.out.println("name : " + name);
		System.out.println("course : " + course.toString());
		bmiCalculator.info();
	}
	
}
