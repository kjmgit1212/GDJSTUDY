package com.goodee.ex01.xml05;

import java.util.List;

public class Exam {

	// field
	private List<Integer> scores;  // 5개의 점수(0~100)
	private double average;        // 평균
	private char grade;            // 학점(A~F)
	
	// getter/setter
	public List<Integer> getScores() {
		return scores;
	}
	public void setScores(List<Integer> scores) {  // <property name="scores">에 의해서 호출
		// 점수는 context05.xml에서 받아온다.
		this.scores = scores;
		// 평균 구하기
		int total = 0;
		int size = scores.size();
		for(int i = 0; i < size; i++) {
			total += scores.get(i);
		}
		average = (double)total / size;
		// 학점 구하기
		if(average >= 90) { grade = 'A'; }
		else if(average >= 80) { grade = 'B'; }
		else if(average >= 70) { grade = 'C'; }
		else if(average >= 60) { grade = 'D'; }
		else { grade = 'F'; }
	}
	public double getAverage() {
		return average;
	}
	public void setAverage(double average) {  // 사용 안 함
		this.average = average;
	}
	public char getGrade() {
		return grade;
	}
	public void setGrade(char grade) {        // 사용 안 함
		this.grade = grade;
	}
	
}
