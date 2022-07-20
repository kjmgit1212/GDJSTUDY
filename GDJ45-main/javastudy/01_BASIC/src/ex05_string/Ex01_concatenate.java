package ex05_string;

public class Ex01_concatenate {

	public static void main(String[] args) {
		
		// 문자열 연결 연산 : +
		
		System.out.println(1 + 1);       // 2
		System.out.println(1 + "1");     // "11"
		System.out.println(1 + 1 + "1"); // "21"
		
		int age = 5;
		String res = (age >= 20 ? "성인" : "미성년자");
		
		System.out.println(age + "살은 " + res + "입니다.");
		
	}

}
