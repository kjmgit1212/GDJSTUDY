package ex21_Object;

public class Main {

	public static void main(String[] args) {
		
		Object obj = new Person();
		
		// 인스턴스 obj는 Object 타입이므로
		// Object 클래스의 메소드만 호출할 수 있다.
		// Person 클래스의 메소드 호출을 위해서 Person 타입으로 캐스팅해야 한다.
		((Person)obj).eat();

	}

}
