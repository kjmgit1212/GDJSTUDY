package ex03_arithmetic;

public class Ex01_operator {

	public static void main(String[] args) {
		
		// 숫자 연산
		int a = 7;
		int b = 2;
		
		int res1 = a + b;
		int res2 = a - b;
		int res3 = a * b;
		int res4 = a / b;  // 몫     - 정수끼리 연산
		int res5 = a % b;  // 나머지 - 정수끼리 연산
		
		System.out.println(res1);
		System.out.println(res2);
		System.out.println(res3);
		System.out.println(res4);
		System.out.println(res5);
		
		double c = 7.0;
		double d = 2.0;
		
		double res6 = c + d;
		double res7 = c - d;
		double res8 = c * d;
		double res9 = c / d;  // 나누기  - 실수끼리 연산
		double res10 = c % d;
		
		System.out.println(res6);
		System.out.println(res7);
		System.out.println(res8);
		System.out.println(res9);
		System.out.println(res10);
		
		
		// 정수와 실수를 섞어서 연산한다면?
		// 1. 자동으로 모든 타입을 실수로 변환한 뒤 연산한다.
		// 2. 강제로 정수로 변환한 뒤 연산할 수도 있다. (casting)
		
		int n1 = 100;
		double n2 = 1.5;
		
		double nRes1 = n1 + n2;
		double nRes2 = n1 - n2;
		double nRes3 = n1 * n2;
		double nRes4 = n1 / n2;
		
		System.out.println(nRes1);
		System.out.println(nRes2);
		System.out.println(nRes3);
		System.out.println(nRes4);
		
		int nRes5 = n1 + (int)n2;
		int nRes6 = n1 - (int)n2;
		int nRes7 = n1 * (int)n2;
		int nRes8 = n1 / (int)n2;
		int nRes9 = n1 % (int)n2;
		
		System.out.println(nRes5);
		System.out.println(nRes6);
		System.out.println(nRes7);
		System.out.println(nRes8);
		System.out.println(nRes9);
		
	}

}
