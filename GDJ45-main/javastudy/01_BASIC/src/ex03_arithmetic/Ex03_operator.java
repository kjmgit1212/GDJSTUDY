package ex03_arithmetic;

public class Ex03_operator {

	public static void main(String[] args) {
		
		// 대입 연산 (전달, 저장)
		// = : 등호의 오른쪽 값을 왼쪽 변수에 넣는다.
		
		int a = 10;
		a = 20;
		System.out.println(a);

		// 유명한 대입 예제
		// 변수에 저장된 값을 교환하시오.
		
		int x = 1;
		int y = 2;
		int t;      // temporary (임시)
		
		t = x;
		x = y;
		y = t;
		
		System.out.println(x);
		System.out.println(y);
		
	}

}
