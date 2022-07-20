package ex01_branch;

public class Ex03_if_elseif {

	public static void main(String[] args) {
		
		// 점수에 따른 학점 출력
		// 0 ~ 100 사이
		// 90 이상 : A
		// 80 이상 : B
		// 70 이상 : C
		// 60 이상 : D
		// 60 미만 : F
		
		int score = 100;
		
		if(score < 0 || score > 100)
			System.out.println(score + "점은 잘못된 점수");
		else if(score >= 90)
			System.out.println(score + "점은 A학점");
		else if(score >= 80)
			System.out.println(score + "점은 B학점");
		else if(score >= 70)
			System.out.println(score + "점은 C학점");
		else if(score >= 60)
			System.out.println(score + "점은 D학점");
		else
			System.out.println(score + "점은 F학점");
		
		// 문제. 1~12사이의 월 정보를 이용해서 해당 계절 출력하기.
		// 봄   : 3~5
		// 여름 : 6~8
		// 가을 : 9~11
		// 겨울 : 12, 1~2
		// 잘못된 정보
		
		int month = 1;
		
		if(month < 1 || month > 12)
			System.out.println("잘못된 정보");
		else if(month >= 3 && month <= 5)
			System.out.println("봄");
		else if(month >= 6 && month <= 8)
			System.out.println("여름");
		else if(month >= 9 && month <= 11)
			System.out.println("가을");
		else
			System.out.println("겨울");
		
		// 나머지 연산자 활용하기(modular 연산)
		if(month < 1 || month > 12)
			System.out.println("잘못된 정보");
		else if(month % 12 <= 2)
			System.out.println("겨울");
		else if(month % 12 <= 5)
			System.out.println("봄");
		else if(month % 12 <= 8)
			System.out.println("여름");
		else
			System.out.println("가을");

	}

}
