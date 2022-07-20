package com.goodee.ex05.domain;

public class BoardDTO {

	private String title;
	private Long hit;
	
	public BoardDTO() {
		
	}
	public BoardDTO(String title, Long hit) {
		super();
		this.title = title;
		this.hit = hit;
	}
	
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
	
}
