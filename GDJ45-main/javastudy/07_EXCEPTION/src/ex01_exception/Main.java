package ex01_exception;

import java.util.Scanner;

public class Main {

	public static void m1() {
		// NullPointerException : null값이 메소드를 호출할 때 발생
		String[] hobbies = {
				"game",
				"running",
				"swimming",
				null,
				"reading"
		};
		for(String hobby : hobbies) {
			if(hobby.equals("reading"))  // if(null.equals("reading"))
				System.out.println("저와 취미가 같군요.");
		}
		/*
		for(String hobby : hobbies) {
			if("reading".equals(hobby))
				System.out.println("저와 취미가 같군요.");
		}
		*/
	}	
	public static void m2() {
		// NumberFormatException : String을 Number타입으로 변환할 때 발생
		Scanner sc = new Scanner(System.in);
		System.out.print("이름(필수) >>> ");
		String name = sc.nextLine();
		System.out.print("나이(선택) >>> ");
		String strAge = sc.nextLine();
		int age = Integer.parseInt(strAge);
		/*
		int age;
		if(strAge.isEmpty())
			age = 0;
		else
			age = Integer.parseInt(strAge);
		*/
		System.out.println("이름 " + name + " 나이 " + age + "살");
		sc.close();
	}
	
	public static void main(String[] args) {
		m2();
	}

}
