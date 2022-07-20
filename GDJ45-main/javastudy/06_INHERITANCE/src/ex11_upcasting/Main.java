package ex11_upcasting;

public class Main {

	public static void main(String[] args) {
	
		// Person을 상속 받는 모든 인스턴스를 Person 타입으로 저장할 수 있다.
		Person p1 = new Student();
		Person p2 = new Alba();
	
		// Person 타입으로 선언되었기 때문에 Person의 메소드만 호출할 수 있다.
		p1.eat();
		p2.eat();
		
	}

}
