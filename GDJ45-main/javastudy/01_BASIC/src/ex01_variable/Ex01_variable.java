package ex01_variable;

// single comment - 한 줄짜리 주석(설명)
/*
    multiple comment
    여러 줄의 주석
*/

/*
    1. 자바 이름 규칙 (naming convention)
        1) 프로젝트 : 마음대로
        2) 패키지 : 모두 소문자로 작성
        3) 클래스 : Upper Camel Case
        4) 변수, 메소드 : Lower Camel Case
        5) 상수 : Snake Case
    2. Naming Convention
        1) Upper Camel Case : MyName
        2) Lower Camel Case : myName
        3) Snake Case : MY_NAME
*/

/*
    main 메소드(method)
    1. 자바 프로젝트가 실행되려면 반드시 필요하다.
    2. JVM(Java Virtual Machine)은 실행할 때 열려 있는 main 메소드를 실행한다.
    3. main 메소드를 못 찾으면 실행에 성공한 최근 main 메소드를 다시 실행한다.
    4. public static void main(String[] args) { }
    5. 클래스를 추가할 때 자동 등록하거나, 빠르게 자동완성 할 수 있다.
*/

public class Ex01_variable {

	public static void main(String[] args) {
		
		// 출력 방법
		// System.out.println(출력할내용)  출력 후 줄 바꿈
		// System.out.print(출력할내용)    출력만 함
		
		// 데이터 작성 방법(literal)
		
		// 숫자
		System.out.println(45);
		System.out.println(1.5);
		System.out.println(45L);
		System.out.println(1.123456789);
		System.out.println(1.12345678901234567890);
		
		// 문자 (1글자)
		System.out.println('a');
		System.out.println('한');
		System.out.println('\n');  // 줄 바꿈(역슬래시로 시작하는 문자 : 이스케이프)
		System.out.println('\'');  // '
		System.out.println('\"');  // "
		
		// 문자열 (여러 글자)
		System.out.println("hello");
		System.out.println("a");
		System.out.println("");  // 빈 문자열
		
		// 논리 (참, 거짓)
		System.out.println(true);   // 참
		System.out.println(false);  // 거짓
		
	}

}
