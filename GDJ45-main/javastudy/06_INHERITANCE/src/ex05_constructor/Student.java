package ex05_constructor;

public class Student extends Person {
	
	private String school;
	
	public Student(String name, String school) {
		// 가장 먼저 Person 클래스의 생성자를 호출해야 한다.
		// 서브 클래스에서 슈퍼 클래스의 생성자를 호출하는 방법
		// 키워드 super를 사용한다.
		super(name);  // public Person(String name){ } 호출하는 코드
		this.school = school;
	}
	
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public void study() {
		System.out.println(school + "학교 다닌다.");
	}

}
