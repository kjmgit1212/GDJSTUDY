package prac2;

public class Ex02 {

	public static void main(String[] args) {
		
		Car car = new Car();
		
		car.setOil(60);  // 0~50
		car.info();
		
		car.speedUp();
		car.info();

		car.speedDown();
		car.info();
		
	}
	
}
