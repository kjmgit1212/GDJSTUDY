package ex01_is_a;

// Student is a Person.

// 자식 클래스
// 1. 서브(sub) 클래스이다.
// 2. 상속 관계를 만들기 위해서 extends 키워드를 추가한다.

public class Student extends Person {

	// Student 클래스의 기능은 Person 클래스의 기능을 포함한다.
	// Person 클래스의 eat 메소드를 Student 클래스의 기능으로 볼 수 있다.
	
	// 따라서, Student 클래스의 기능은 eat, study 이다.
	
	public void study() {
		System.out.println("공부한다.");
	}
	
}
