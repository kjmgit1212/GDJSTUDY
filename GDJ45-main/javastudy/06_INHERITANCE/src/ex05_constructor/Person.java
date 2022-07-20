package ex05_constructor;

public class Person {

	private String name;

	// 슈퍼 클래스의 생성자가 디폴트 형식이 아니기 때문에
	// 서브 클래스의 생성자에서는 반드시 Person의 생성자 호출이 필요하다.
	public Person(String name) {
		super();
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void eat() {
		System.out.println(name + "가 먹는다.");
	}
	
}
