package ex15_downcasting;

public class Main {

	public static void main(String[] args) {
		
		// 10대의 자동차(Car, Ev, Hybrid)를 저장할 수 있는 배열 생성
		Car[] cars = new Car[10];
		
		// 10% 확률 Car, 30% 확률 Ev, 60% 확률 Hybrid
		for(int i = 0; i < cars.length; i++) {
			if(Math.random() < 0.1)
				cars[i] = new Car();
			else if(Math.random() < 0.4)
				cars[i] = new Ev();
			else
				cars[i] = new Hybrid();
		}
		
		// Car이면 drive() 호출
		// Ev이면 charge() 호출
		// Hybrid이면 addOil() 호출
		// 가장 아래 후손부터 확인해야 한다.
		for(int i = 0; i < cars.length; i++) {
			if(cars[i] instanceof Hybrid)
				((Hybrid)cars[i]).addOil();
			else if(cars[i] instanceof Ev)
				((Ev)cars[i]).charge();
			else if(cars[i] instanceof Car)
					cars[i].drive();
		}
		
	}

}
