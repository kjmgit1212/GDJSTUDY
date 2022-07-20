package com.goodee.ex01.xml02;

public class Engine {
	
	// field
	private String fuel;       // 연료(디젤)
	private double efficency;  // 연비(10.5)
	private int hp;            // 출력(340)
	private int cc;            // 배기량(2993)

	// getter/setter
	public String getFuel() {
		return fuel;
	}
	public void setFuel(String fuel) {
		this.fuel = fuel;
	}
	public double getEfficency() {
		return efficency;
	}
	public void setEfficency(double efficency) {
		this.efficency = efficency;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getCc() {
		return cc;
	}
	public void setCc(int cc) {
		this.cc = cc;
	}
	
	// info() 메소드
	public void info() {
		System.out.println("연료타입: " + fuel);
		System.out.println("연비: " + efficency + "km/l");
		System.out.println("출력: " + hp + "hp");
		System.out.println("배기량: " + cc + "cc");
	}
	
}
