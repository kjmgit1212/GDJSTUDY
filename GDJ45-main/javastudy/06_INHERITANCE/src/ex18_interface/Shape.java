package ex18_interface;

public interface Shape {

	// final 상수
	public final double PI = Math.PI;
	
	// 추상 메소드
	public double getArea();  // public abstract double getArea();
	public void info();  // public abstract void info();
	
	// default 메소드
	public default void message() {
		System.out.println("나는 도형입니다.");
	}
	
}
