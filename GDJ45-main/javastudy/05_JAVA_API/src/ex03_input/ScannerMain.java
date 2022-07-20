package ex03_input;

import java.util.Scanner;

public class ScannerMain {

	public static void main(String[] args) {
		
		// java.util 패키지에 저장된 Scanner 클래스
		Scanner sc = new Scanner(System.in);

		// 입력 메소드
		// 1. int    : nextInt()
		// 2. long   : nextLong()
		// 3. double : nextDouble()
		// 4. String
		//    1) nextLine() : Enter키를 입력의 끝으로 본다.(공백 입력 가능)
		//    2) next() : 공백문자(Enter, Space 등)를 입력의 끝으로 본다. (공백 입력 불가능)
		
		System.out.println("이름을 입력하세요");
		String name = sc.next();  // 뽀 로 로 Enter  => 뽀로로만 가져감.
		
		sc.nextLine();  // 남겨진 Enter 가져감. (오직 nextLine()만 Enter를 먹을 수 있음)
		
		System.out.println("주소를 입력하세요");
		String address = sc.nextLine();
		
		System.out.println("나이를 입력하세요");
		int age = sc.nextInt();
		
		System.out.println("키를 입력하세요");
		double height = sc.nextDouble();
		
		System.out.println("성별을 입력하세요");
		char gender = sc.next().charAt(0);
		
		System.out.println(name);
		System.out.println(address);
		System.out.println(age);
		System.out.println(height);
		System.out.println(gender);
		
		sc.close();  // 연결 해제. 안 하면 JVM이 스스로 처리함.
		
	}

}
