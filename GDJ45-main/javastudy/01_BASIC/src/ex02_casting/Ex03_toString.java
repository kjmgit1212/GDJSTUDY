package ex02_casting;

public class Ex03_toString {

	public static void main(String[] args) {
		
		// 문자열로 변환하기
		// 1. String 클래스의 valueOf 메소드
		// 2. 객체(변수)의 toString 메소드
		// 3. 빈 문자열("")
		
		// 1. String 클래스의 valueOf 메소드
		int a = 100;
		String str1 = String.valueOf(a);
		
		System.out.println(str1);
		
		double b = 1.5;
		String str2 = String.valueOf(b);
		
		System.out.println(str2);
		
		
		// 2. 객체(변수)의 toString 메소드
		Long c = 200L;
		String str3 = c.toString();
		
		System.out.println(str3);
		
		
		// 3. 빈 문자열("")
		// 모든 데이터는 빈 문자열("")을 더하면(+) 문자열이 됨
		// 개발자들이 가장 선호하는 방식
		
		boolean d = true;
		String str4 = d + "";
		
		System.out.println(str4);
		
	}

}
