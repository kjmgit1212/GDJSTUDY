package ex01_variable;

public class Ex02_variable {

	public static void main(String[] args) {
		
		// 변수(variable)
		// 1. 컴퓨터의 메모리(램)에 확보되는 데이터 저장 공간
		// 2. 메모리(램)는 1바이트마다 고유번호(0부터 시작하는 16진수)를 부여
		// 3. 주소(address) : 1바이트마다 부여된 메모리의 고유번호
		
		// 1. byte 타입
		//    1) 1바이트
		//    2) 정수
		//    3) 모든 데이터의 최소 단위를 의미함
		
		// 2. short 타입
		//    1) 2바이트
		//    2) 정수
		//    3) 과거용

		// 3. int 타입
		//    1) 4바이트
		//    2) 정수 (기본 단위)
		//    3) 가용범위 : -2,147,483,648 ~ 2,147,483,647
		
		int myAge = 45;
		System.out.println(myAge);
		
		// 4. long 타입
		//    1) 8바이트
		//    2) 정수
		//    3) suffix(접미사)가 L(l)
		
		long myMoney = 50000L;
		System.out.println(myMoney);
		
		// 1바이트 = 8비트
		
		//          갯수       범위
		// 1비트    2         
        // 2비트    4         
		// 3비트    8         
		// ...
		// n비트    2의 n제곱  -(2의 n-1제곱) ~ (2의 n-1제곱) - 1
		
		
		// 5. float 타입
		//    1) 4바이트
		//    2) 실수
		//    3) suffix(접미사)가 F(f), 과거용
		
		// 6. double 타입
		//    1) 8바이트
		//    2) 실수 (기본 단위)
		
		double myWeight = 72.5;
		System.out.println(myWeight);
		
		
		// 7. char 타입
		//    1) 2바이트
		//    2) 문자
		//    3) 문자는 실제로 저장될 때는 정수(문자의 코드값)로 처리

		//       문자       코드값
		//       '0'        48
		//       'A'        65
		//       'a'        97
		
		char myGender = 'M';
		System.out.println(myGender);
		
		
		// 8. boolean 타입
		//    1) true, false 중 하나만 저장
		//    2) 논리
		
		boolean isKorean = true;
		System.out.println(isKorean);
		
		
		// 9. String 클래스 타입
		//    1) 기본 타입(primitive type)이 아님
		//    2) 문자열
		//    3) 참조 타입(reference type) : 데이터가 아닌 데이터의 참조 값(주소 값)을 저장
		
		String myName = "MIN";
		System.out.println(myName);		
		
	}

}
