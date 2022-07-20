package ex02_try_catch;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void m1() {
		// NullPointerException 처리
		try {
			String[] hobbies = new String[3];
			hobbies[0] = "운동";
			hobbies[1] = "운동";
			hobbies[2] = "운동";
			for(String hobby : hobbies) {
				System.out.println(hobby.substring(0, 2));
			}
			System.out.println("취미 출력이 끝났습니다.");
		} catch(NullPointerException e) {
			System.out.println("NullPointerException 발생");
		}
	}
	
	public static void m2() {
		// NumberFormatException 처리
		try {
			String input = "20,21,22,23,24";
			String[] inputs = input.split(",");
			int[] ages = new int[inputs.length];
			for(int i = 0; i < inputs.length; i++) {
				ages[i] = Integer.parseInt(inputs[i]);
				System.out.println((i + 1) + "번째 입력 나이 " + ages[i]);
			}
		} catch(NumberFormatException e) {
			System.out.println("입력은 모두 정수입니다.");
		}
	}
	
	public static void m3() {
		// Scanner 인스턴스를 이용해서 2개의 정수를 입력 받는다.
		// +, -, *, /, % 연산 결과를 출력한다.
		try {
			Scanner sc = new Scanner(System.in);
			System.out.print("정수1 >>> ");
			int a = sc.nextInt();
			System.out.print("정수2 >>> ");
			int b = sc.nextInt();
			System.out.println(a + "+" + b + "=" + (a + b));
			System.out.println(a + "-" + b + "=" + (a - b));
			System.out.println(a + "*" + b + "=" + (a * b));
			System.out.println(a + "/" + b + "=" + (a / b));
			System.out.println(a + "%" + b + "=" + (a % b));
			sc.close();
		} catch(ArithmeticException e) {
			System.out.println("정수2는 0일 수 없습니다.");
		} catch(InputMismatchException e) {
			System.out.println("정수만 입력할 수 있습니다.");
		}
	}
	
	public static void m4() {
		// 모든 예외는 Exception 클래스로 처리할 수 있다.
		try {
			int[] arr = new int[3];
			arr[0] = 100;
			arr[1] = 200;
			arr[2] = 300;
			arr[3] = 400;  // 존재하지 않는 요소
		} catch(Exception e) {
			System.out.println("예외가 발생했습니다.");
		}
	}
	
	public static void main(String[] args) {
		m4();
	}

}
