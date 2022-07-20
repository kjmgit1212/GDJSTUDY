package ex04_logic;

public class Ex02_logic {

	public static void main(String[] args) {
		
		// 논리 연산
		// 1. && : 논리 AND, 2개 이상의 크기 비교에서 사용,      모든 비교 결과가 true이면 &&의 결과가 true
		// 2. || : 논리 OR,  2개 이상의 크기 비교에서 사용, 하나 이상 비교 결과가 true이면 ||의 결과가 true
		// 3. !  : 논리 NOT, 1개의 크기 비교 결과를 반대로 변경
		
		int a = 10;
		int b = 10;
		
		boolean res1 = (a == 10 && b == 10);
		boolean res2 = (a == 10 && b == 90);
		System.out.println(res1);
		System.out.println(res2);
		
		boolean res3 = (a == 10 || b == 10);
		boolean res4 = (a == 10 || b == 90);
		boolean res5 = (a == 90 || b == 90);
		System.out.println(res3);
		System.out.println(res4);
		System.out.println(res5);
		
		boolean res6 = !(a == 10);
		boolean res7 = !(a == 90);
		System.out.println(res6);
		System.out.println(res7);
		
		// 지금부터 신경쓰기
		
		// && 연산은 false가 발생하면 더 이상 연산을 진행하지 않는다.
		boolean res8 = (a == 90 && ++b > 10);
		System.out.println(res8);
		System.out.println(b);
		
		// || 연산은 true가 발생하면 더 이상 연산을 진행하지 않는다.
		boolean res9 = (a == 10 || ++b > 10);
		System.out.println(res9);
		System.out.println(b);
		
	}

}
