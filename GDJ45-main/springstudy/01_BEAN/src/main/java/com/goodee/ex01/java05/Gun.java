package com.goodee.ex01.java05;

public class Gun {

	// field
    private String model;
    private int bullet;
    
    // constructor
    public Gun() {  // default constructor는 setter injection에서 사용됨.
		
	}
	public Gun(String model, int bullet) {  // constructor injection에서 사용됨.
		super();
		this.model = model;
		this.bullet = bullet;
	}
	
	// getter/setter
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getBullet() {
		return bullet;
	}
	public void setBullet(int bullet) {
		this.bullet = bullet;
	}
	
}
