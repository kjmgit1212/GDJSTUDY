package com.goodee.ex01.xml01;

public class Calculator {

	// constructor (디폴트 생성자)
	public Calculator() {
		
	}
	
	// method
	public void add(int a, int b) {
		System.out.println(a + "+" + b + "=" + (a + b));
	}
	public void sub(int a, int b) {
		System.out.println(a + "-" + b + "=" + (a - b));
	}
	public void mul(int a, int b) {
		System.out.println(a + "*" + b + "=" + (a * b));
	}
	public void div(int a, int b) {
		System.out.println(a + "/" + b + "=" + (a / b));
	}
	public void mod(int a, int b) {
		System.out.println(a + "%" + b + "=" + (a % b));
	}
	
}
