package com.goodee.ex01.java02;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Student {

	// field
	private List<Integer> scores;      // 0~100 사이 랜덤 5개 점수
	private Set<String> awards;        // 임의의 상장 3개
	private Map<String, String> home;  // 주소, 전화번호
	
	// getter/setter
	public List<Integer> getScores() {
		return scores;
	}
	public void setScores(List<Integer> scores) {
		this.scores = scores;
	}
	public Set<String> getAwards() {
		return awards;
	}
	public void setAwards(Set<String> awards) {
		this.awards = awards;
	}
	public Map<String, String> getHome() {
		return home;
	}
	public void setHome(Map<String, String> home) {
		this.home = home;
	}
	
}
