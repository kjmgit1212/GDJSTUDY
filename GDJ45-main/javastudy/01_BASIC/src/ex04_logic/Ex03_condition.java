package ex04_logic;

public class Ex03_condition {

	public static void main(String[] args) {
		
		// 조건 연산
		// 1. 결과가 2개 이상인 경우(예: 점수에 따라 "합격", "불합격")에 사용한다.
		// 2. 3개의 항목이 사용되기 때문에 삼항 연산이라고도 한다.
		// 3. 형식
		//    비교연산 ? true : false
		//    점수>=60 ? 합격 : 불합격
		
		int score = 50;
		String res1 = (score >= 60 ? "합격" : "불합격");
		
		System.out.println(res1);
		
		
		// 문제. 임의의 정수를 홀수, 짝수로 구분하여 출력하자.
		// 홀수 : 2로 나눈 나머지가 1인 수
		// 짝수 : 2로 나눈 나머지가 0인 수
		int n = 6;
		String res2 = (n % 2 == 1 ? "홀수" : "짝수");		
		String res3 = (n % 2 == 0 ? "짝수" : "홀수");
		
		System.out.println(res2);
		System.out.println(res3);
		
		// 문제. 3의 배수 여부를 구분하여 출력하자.
		// 0은 3의 배수가 아니다.
		// 3의 배수 : 3으로 나눈 나머지가 0인 수
		String res4 = (n != 0 && n % 3 == 0 ? "3의배수" : "3의배수아님");
		
		System.out.println(res4);

	}

}
