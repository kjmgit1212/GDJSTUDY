package ex06_static;

public class Calculator {

	
	// static
	// 1. static 요소(필드, 메소드)는 메모리에 미리 만들어진다.
	// 2. 인스턴스 생성 이전에 사용할 수 있다.
	// 3. 1개만 만들어 진다.
	// 4. 클래스를 이용해서 호출한다.
	// 5. 클래스 변수, 클래스 메소드라고 부른다.
	
	
	// method
	
	public static void add(int a, int b) {
		System.out.println(a + "+" + b + "=" + (a + b));
	}
	public static void sub(int a, int b) {
		System.out.println(a + "-" + b + "=" + (a - b));
	}
	public static void mul(int a, int b) {
		System.out.println(a + "x" + b + "=" + (a * b));
	}
	public static void div(int a, int b) {
		System.out.println(a + "/" + b + "=" + (a / b));
	}
	public static void mod(int a, int b) {
		System.out.println(a + "%" + b + "=" + (a % b));
	}
	public static void pow(int a, int b) {
		// a의 b제곱
		// 2의 3제곱
		// res = res * 2;
		// res = res * 2;
		// res = res * 2;
		int res = 1;
		for(int i = 0; i < b; i++)
			res *= a;
		System.out.println(a + "의 " + b + "제곱=" + res);
	}
	public static void abs(int a) {
		// a의 절대값
		int res = (a >= 0 ? a : -a);
		System.out.println(a + "의 절대값=" + res);
	}
	
}
