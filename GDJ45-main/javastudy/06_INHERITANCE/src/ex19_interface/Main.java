package ex19_interface;

public class Main {

	public static void main(String[] args) {
		
		// Phone 타입
		Phone p1 = new SmartPhone();
		p1.call();
		p1.sms();
		((Computer)p1).playMusic();
		((Computer)p1).playGame();
		
		// Computer 타입
		Computer p2 = new SmartPhone();
		p2.playMusic();
		p2.playGame();
		((Phone)p2).call();
		((Phone)p2).sms();
		
		// SmartPhone 타입
		SmartPhone p3 = new SmartPhone();
		p3.call();
		p3.sms();
		p3.playMusic();
		p3.playGame();

	}

}
