package ex14_downcasting;

public class Main {

	public static void main(String[] args) {

		Person p = new Alba();
		
		p.eat();
		
		if (p instanceof Alba) {  // p는 Alba 인스턴스인가?
			((Alba) p).study();
			((Alba) p).work();
		}

	}

}
