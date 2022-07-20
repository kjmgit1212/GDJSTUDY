package com.goodee.ex01.xml01;

// Calculator 기능을 내장한 공학용 계산기
public class EngineerCalculator {
	
	// field
	private int a;
	private int b;
	private Calculator calculator;
	
	// default constructor
	public EngineerCalculator() {
		
	}
	
	// constructor using fields
	public EngineerCalculator(int a, int b, Calculator calculator) {
		super();
		this.a = a;
		this.b = b;
		this.calculator = calculator;
	}


	// method
	public void add() {
		calculator.add(a, b);
	}
	public void sub() {
		calculator.sub(a, b);
	}
	public void mul() {
		calculator.mul(a, b);
	}
	public void div() {
		calculator.div(a, b);
	}
	public void mod() {
		calculator.mod(a, b);
	}

	// getter/setter
	public int getA() {
		return a;
	}
	public void setA(int a) {
		this.a = a;
	}
	public int getB() {
		return b;
	}
	public void setB(int b) {
		this.b = b;
	}
	public Calculator getCalculator() {
		return calculator;
	}
	public void setCalculator(Calculator calculator) {
		this.calculator = calculator;
	}

}
