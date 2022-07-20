package ex01_field;

public class MemberMain {

	public static void main(String[] args) {
		
		// Member : 클래스         - 타입
		// m      : 인스턴스(객체) - 변수
		Member m = new Member();
		
		// 인스턴스가 가지고 있는 필드 값은 마침표(.) 표기법으로 확인
		System.out.println(m);         // 클래스명@참조값
		System.out.println(m.userId);
		System.out.println(m.name);
		System.out.println(m.age);
		System.out.println(m.height);
		System.out.println(m.isVIP);

		// 인스턴스에 값을 전달하기
		m.userId = "manager";
		m.name = "관리자";
		m.age = 45;
		m.height = 180.5;
		m.isVIP = true;
		System.out.println(m.userId);
		System.out.println(m.name);
		System.out.println(m.age);
		System.out.println(m.height);
		System.out.println(m.isVIP);
		
	}

}
