package ex07_constructor;

public class Rectangle {

	private int width;   // 너비
	private int height;  // 높이
	// private int area; 계산할 수 있는 항목이므로 필드로 둘 필요는 없다.

	public Rectangle(int width, int height) {
		super();
		this.width = width;
		this.height = height;
	}
	
	public int getArea() {
		return width * height;
	}
	public void info() {
		System.out.println("너비 " + width + ", 높이 " + height + ", 넓이 " + getArea());
	}
	
}
