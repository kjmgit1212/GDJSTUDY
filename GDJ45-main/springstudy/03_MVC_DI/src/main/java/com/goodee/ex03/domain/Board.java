package com.goodee.ex03.domain;

public class Board {
	
	// field
	private String title;
	private String content;
	
	// constructor
	public Board() {
	
	}
	public Board(String title, String content) {
		super();
		this.title = title;
		this.content = content;
	}

	// getter/setter
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
