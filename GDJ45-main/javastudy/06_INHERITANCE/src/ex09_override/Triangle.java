package ex09_override;

public class Triangle extends Shape {

	private int width;
	private int height;
	
	public Triangle(String type, int width, int height) {
		super(type);
		this.width = width;
		this.height = height;
	}
	
	@Override
	public double getArea() {
		return width * height * 0.5;
	}
	@Override
	public void info() {
		super.info();
		System.out.println("너비 " + width + ", 높이 " + height + ", 넓이 " + getArea());
	}
	
}
