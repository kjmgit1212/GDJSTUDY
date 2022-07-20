package ex02_loop;

public class Ex01_for {

	public static void main(String[] args) {


		// for
		// 1. 특정 실행문을 여러 번 반복하는 경우에 사용한다.
		// 2. 형식
		//    for(초기화; 조건식; 증감식)
		//        실행문

		
		// 1부터 10까지 출력
		/*
		for(int n = 1; n <= 10; n++)
			System.out.println(n);
		*/
		
		// 10부터 1까지 거꾸로 출력
		/*
		for(int n = 10; n >= 1; n--)
			System.out.println(n);
		*/
		
		// 문제. 구구단 7단을 모두 출력하기
		// 7x1=7
		// ...
		/*
		int dan = 7;
		for(int n = 1; n <= 9; n++)
			System.out.println(dan + "x" + n + "=" + (dan * n));
		*/
		
		// 문제. 1~100 사이의 모든 정수를 다 더하기
		// hap += 1
		// hap += 2
		// ...
		/*
		int hap = 0;
		for (int n = 1; n <= 100; n++)
			hap += n;
		
		System.out.println(hap);
		*/
		
		// 문제. 1~100 사이의 모든 3의 배수를 출력하기
		/*
		for(int n = 1; n <= 100; n++)
			if(n % 3 == 0)
				System.out.println(n);
		*/
		
		// 문제. begin부터 end까지 모두 더하기.
		// 단, begin이 end보다 작다는 보장은 없음.
		// begin=1,  end=10, hap=55
		// begin=10, end=1,  hap=55
		
		int begin = 10;
		int end = 1;
		int hap = 0;
		
		// 만약, begin > end이면 begin과 end를 교환해서 처리.
		int temp;    // temporary
		
		if(begin > end) {
			temp = begin;
			begin = end;
			end = temp;
		}
		
		for(int n = begin; n <= end; n++)
			hap += n;
		
		System.out.println(begin + "부터 " + end + "까지 합은 " + hap + "입니다.");
		
	}

}
