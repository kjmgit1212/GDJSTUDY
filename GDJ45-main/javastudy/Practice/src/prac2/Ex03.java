package prac2;

public class Ex03 {

	public static void main(String[] args) {
		
		Student student = new Student("홍길동");
		Exam exam1 = new Exam();
		Exam exam2 = new Exam();
		
		student.doExam(exam1);
		student.doExam(exam2);
		student.info();

	}

}
