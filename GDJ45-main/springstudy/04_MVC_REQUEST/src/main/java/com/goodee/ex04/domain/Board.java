package com.goodee.ex04.domain;

public class Board {

	// field
	private String title;
	private Long hit;
	
	// default constructor
	public Board() {
	
	}
	
	// getter/setter
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getHit() {
		return hit;
	}
	public void setHit(Long hit) {
		this.hit = hit;
	}
	
	
	/* Builder Pattern */
	
	// constructor
	public Board(Builder builder) {
		this.title = builder.title;
		this.hit = builder.hit;
	}

	// builder() 메소드
	public static Builder builder() {
		return new Builder();
	}
	
	// Builder 클래스 (클래스 안에 클래스를 넣는 내부 클래스)
	public static class Builder {
		
		// 내부 field : 여기에 입력 받은 값을 Board의 field로 보냅니다.
		private String title;
		private Long hit;
		
		// title() 메소드
		public Builder title(String title) {
			this.title = title;
			return this;  // this는 Builder 클래스를 의미합니다.
			              // return this;는 메소드 체이닝(메소드 계속 계속 부르기)이 필요할 때 사용합니다.
		}
		
		// hit() 메소드
		public Builder hit(Long hit) {
			this.hit = hit;
			return this;
		}
		
		// build() 메소드
		public Board build() {
			return new Board(this);  // this는 Builder이므로 public Board(Builder builder) { } 생성자가 필요하다.
		}
		
	}
	
}
