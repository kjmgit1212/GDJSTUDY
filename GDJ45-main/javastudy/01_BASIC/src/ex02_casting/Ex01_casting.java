package ex02_casting;

public class Ex01_casting {

	public static void main(String[] args) {
		
		// casting
		// 1. 어떤 변수를 다른 타입으로 변경하는 작업이다.
		// 2. 타입을 변경할 변수 앞에 괄호를 붙이고 변경할 타입을 적는다.
		// 3. casting 순간에만 잠시 변경되고 원래 타입은 유지된다.
		
		int a = 100;
		
		System.out.println((double)a);  // double로 형변환(casting)
		System.out.println((char)a);    // char로 형변환(casting)
		
		
		double b = 99.9;
		
		System.out.println((int)b);     // 실수가 정수로 casting 될 때는 소수 이하 자릿수가 모두 잘림
		
		
		// boolean 타입은 casting 없다.
		
		// String 타입은 별도로 처리한다.
		
	}

}