package ex20_interface;

public class Main {

	public static void main(String[] args) {
		
		Dog dog = new Dog("백구");
		Cat cat = new Cat("냥구");
		Snake snake = new Snake("낼름");
		
		Person person = new Person();
		person.feed(dog, "강아지먹이");  // 백구에게 강아지먹이를 준다.
		person.feed(cat, "고양이먹이");  // 냥구에게 고양이먹이를 준다.
		person.feed(snake, "뱀먹이");    // 낼름에게 뱀먹이를 준다.
		
		person.walk(dog);  // 백구와 산책하기
		person.walk(cat);  // 냥구와 산책하기
		// person.walk(snake);  // 실행을 막으시오!
		
	}

}
