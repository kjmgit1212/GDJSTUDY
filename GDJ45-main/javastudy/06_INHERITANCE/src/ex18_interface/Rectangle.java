package ex18_interface;

// 인터페이스 Shape을 상속받는다.(X)
// 인터페이스 Shape을 구현한다.(O)

public class Rectangle implements Shape {

	private int width;
	private int height;
	
	public Rectangle(int width, int height) {
		super();
		this.width = width;
		this.height = height;
	}

	@Override
	public double getArea() {
		return width * height;
	}

	@Override
	public void info() {
		System.out.println("너비 " + width + " 높이 " + height + " 넓이 " + getArea());
	}
	
}
