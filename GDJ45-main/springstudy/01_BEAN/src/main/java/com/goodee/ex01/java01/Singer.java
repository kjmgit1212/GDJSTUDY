package com.goodee.ex01.java01;

public class Singer {

	// field
	private String name;  // 이름
	private Song song;    // 대표곡

	// constructor
	public Singer(String name, Song song) {
		super();
		this.name = name;
		this.song = song;
	}

	// info() 메소드
	public void info() {
		System.out.println("name : " + name);
		System.out.println("title : " + song.getTitle());
		System.out.println("genre : " + song.getGenre());
	}
	
}
