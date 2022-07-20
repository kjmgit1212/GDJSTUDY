package ex03_constructor;

public class Computer {

	// 필드
	String maker;
	String model;
	int price;
	
	// 생성자(constructor)
	// 1. new를 이용해서 인스턴스를 생성할 때만 호출된다.
	// 2. 생성자명은 클래스와 같다.
	// 3. 반환값/반환타입이 존재하지 않는다.
	// 4. 형식
	//    클래스명(매개변수) {
	//        실행코드
	//    }
	// 5. 생성자는 필수로 필요하다.
	//    단, 매개변수가 없는 생성자는 개발자가 안 만들면 자동으로 JVM이 만든다.
	//    이런 생성자를 "디폴트 생성자"라고 한다.
	//    디폴트 생성자 : 매개변수와 실행코드가 없는 생성자.
	
	Computer(String maker, String model, int price) {
		
		System.out.println("Computer 인스턴스가 생성되었습니다.");
		System.out.println("제조사: " + maker);
		System.out.println("모델명: " + model);
		System.out.println("가  격: " + price);
		
		// 매개변수로 전달된 값을 다시 필드로 전달해야 한다.
		// 매개변수와 필드명은 일반적으로 같은 이름을 쓴다.
		// 매개변수와 필드명의 구분을 위해서 this 키워드를 활용한다.
		
		// 기억!
		// 필드 앞에 this를 추가할 수 있다.
		
		this.maker = maker;
		this.model = model;
		this.price = price;
		
	}
	
}
