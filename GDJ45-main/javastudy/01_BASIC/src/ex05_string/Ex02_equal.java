package ex05_string;

public class Ex02_equal {

	public static void main(String[] args) {
		
		String name1 = "alice";
		String name2 = "jessica";
		
		// 주의!
		// 문자열의 동등 비교는 ==를 사용하지 않는다.
		System.out.println(name1 == name2);  // 이름을 비교하지 않는다. 이름이 저장된 주소값(참조값)을 비교한다.
		
		// 해결!
		// 문자열의 동등 비교는 String 클래스의 equals 메소드를 이용한다.
		System.out.println(name1.equals(name2));
		
		
		// 문제. 남자는 왼쪽, 여자는 오른쪽
		String gender = "남자";
		
		System.out.println(gender.equals("남자") ? "왼쪽" : "오른쪽");
		System.out.println(gender.equals("여자") ? "오른쪽" : "왼쪽");

		// System.out.println( !gender.equals("여자") ? "왼쪽" : "오른쪽");
		
	}

}
