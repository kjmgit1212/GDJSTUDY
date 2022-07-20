package prac2;

public class Student {

	// field
	private String name;
	private Exam[] exams;
	private int idx;
	
	// constructor
	public Student(String name) {
		this.name = name;
		exams = new Exam[2];
	}
	
	// method
	public void doExam(Exam ex) {
		if(idx == exams.length)
			return;
		exams[idx++] = ex;
	}
	public double getAverage() {
		// 각 Exam의 인스턴스 : exams[0], exams[1]
		// 각 Exam의 평균     : exams[0].getAverage(), exams[1].getAverage()
		double total = 0;
		for(int i = 0; i < idx; i++)
			total += exams[i].getAverage();
		return total / idx;
	}
	public String getGrade() {
		double average = getAverage();
		if(average >= 90)
			return "A";
		else if(average >= 80)
			return "B";
		else if(average >= 70)
			return "C";
		else if(average >= 60)
			return "D";
		else
			return "F";
	}
	public void info() {
		System.out.println("학생명 " + name);
		// 각 Exam의 인스턴스 : exams[0], exams[1]
		// 각 Exam의 정보     : exams[0].info(), exams[1].info()
		for(int i = 0; i < idx; i++) {
			System.out.println("===" + (i + 1) + "번째 시험 정보===");
			exams[i].info();
		}
		System.out.println("최종 평균 및 학점 " + getAverage() + "점(" + getGrade() + "학점)");
	}
	
}
