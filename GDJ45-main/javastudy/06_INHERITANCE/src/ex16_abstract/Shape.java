package ex16_abstract;

public abstract class Shape {  // 추상 클래스

	private String name;

	public Shape(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public abstract double getArea();  // 추상 메소드
	
}
