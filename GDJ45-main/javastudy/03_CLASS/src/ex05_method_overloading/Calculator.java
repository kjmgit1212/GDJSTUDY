package ex05_method_overloading;

public class Calculator {

	// method
	
	// 메소드 오버로딩
	// 1. 같은 이름의 메소드를 2개 이상 만들 수 있다.
	// 2. 메소드명은 같고, 반드시 매개변수를 서로 달라야 한다.
	// 3. 반환타입은 전혀 상관 없다.
	
	public void add(int a, int b) {
		System.out.println(a + "+" + b + "=" + (a + b));
	}
	
	public void add(int a, int b, int c) {
		System.out.println(a + "+" + b + "+" + c + "=" + (a + b + c));
	}
	
	public void add(double a, double b) {
		System.out.println(a + "+" + b + "=" + (a + b));
	}
	
}
