package ex01_random;

public class MathRandom {

	public static void main(String[] args) {
		
		// 0.0 <= Math.random() < 1.0
				
		// 1. 확률 처리
		// 10% 확률로 "대박", 나머지 확률로 "쪽박"
		if(Math.random() < 0.1)
			System.out.println("대박");
		else
			System.out.println("쪽박");
		
		
		// 2. 정수 난수
		
		// 1, 2, 3, 4, 5 중 하나의 난수 발생
		// Math.random()                          0.0 <= 난수 < 1.0
		// Math.random() * 5                      0.0 <= 난수 < 5.0
		// (int)(Math.random() * 5)                 0 <= 난수 < 5
		// (int)(Math.random() * 5) + 1             1 <= 난수 < 6
		
		System.out.println((int)(Math.random() * 5) + 1);
		
		
		// 문제. 주사위 3개 던지기
		for(int i = 0; i < 3; i++) {
			System.out.println((i + 1) + "번째 주사위 : " + ((int)(Math.random() * 6) + 1));
		}
		
		// 문제. 6자리 인증번호 만들기
		String code = "";
		for(int i = 0; i < 6; i++)
			code += (int)(Math.random() * 10);
		
		System.out.println("전송된 인증번호는 " + code + "입니다.");
		
	}

}
