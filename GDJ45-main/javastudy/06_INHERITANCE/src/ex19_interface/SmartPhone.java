package ex19_interface;

// Phone을 상속 받고,
// Computer를 구현하는 SmartPhone 클래스

// 상속 먼저, 구현 나중

public class SmartPhone extends Phone implements Computer {

	@Override
	public void playMusic() {
		System.out.println("음악듣기");
	}

	@Override
	public void playGame() {
		System.out.println("게임하기");
	}

}
