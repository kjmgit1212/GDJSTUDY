package prac2;

// speed : 0~100km
// oil   : 0~50L

// speedUp : speed 10km 증가
//           oil 1L 감소

// speedDown : speed 10km 감소
//             oil 감소 없음

public class Car {

	// field
	private int speed;  // 0
	private int oil;    // 0
	
	public final int MAX_SPEED = 100;  // 상수는 public 처리 무방
	public final int MAX_OIL = 50;     // 상수는 public 처리 무방
	
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		if(speed > MAX_SPEED) {
			this.speed = MAX_SPEED;
			return;
		}
		this.speed = speed;
	}
	public int getOil() {
		return oil;
	}
	public void setOil(int oil) {
		if(oil > MAX_OIL) {
			this.oil = MAX_OIL;
			return;
		}
		this.oil = oil;
	}
	public void speedUp() {
		if(oil > 0) {
			speed += 10;
			oil--;
			if(speed > MAX_SPEED)
				speed = MAX_SPEED;
		}
	}
	public void speedDown() {
		speed -= 10;
		if(speed < 0)
			speed = 0;
	}
	public void info() {
		System.out.println("속도: " + speed + "km, 연료: " + oil + "L");
	}
	
}
