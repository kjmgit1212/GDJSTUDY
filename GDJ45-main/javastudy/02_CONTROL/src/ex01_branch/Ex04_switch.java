package ex01_branch;

public class Ex04_switch {

	public static void main(String[] args) {
		
		
		// switch
		// 1. 표현식의 결과 값에 따른 분기를 처리한다.
		// 2. 표현식의 결과는 int, char, String이 가능하다.
		// 3. 표현식의 결과는 double, boolean이 불가능하다.
		
		
		// 짝수, 홀수
		int n = 3;
		switch(n % 2) {
		case 0:     // n % 2의 결과가 0이면 여기서부터 실행하시오.
			System.out.println("짝수");
			break;  // switch문을 종료하시오.
		case 1:     // n % 2의 결과가 1이면 여기서부터 실행하시오.
			System.out.println("홀수");
		}
		
		// 3의배수, 3의배수아님
		switch(n % 3) {
		case 0:
			System.out.println("3의배수");
			break;
		case 1:
		case 2:
			System.out.println("3의배수아님");
		}
		
		// 문제. 점수에 따른 학점
		int score = 100;
		
		switch(score / 10) {
		case 10:
		case 9:
			System.out.println("A");
			break;
		case 8:
			System.out.println("B");
			break;
		case 7:
			System.out.println("C");
			break;
		case 6:
			System.out.println("D");
			break;
		default:     // 이외의 모든 경우
			System.out.println("F");
		}
		
		// 문제. 계절
		int month = 1;
		
		switch(month) {
		case 12:
		case 1:
		case 2:
			System.out.println("겨울");
			break;
		case 3: case 4: case 5:
			System.out.println("봄");
			break;
		case 6: case 7: case 8:
			System.out.println("여름");
			break;
		case 9: case 10: case 11:
			System.out.println("가을");
		}

	}

}
