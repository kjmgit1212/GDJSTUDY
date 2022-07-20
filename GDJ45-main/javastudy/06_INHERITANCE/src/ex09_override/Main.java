package ex09_override;

public class Main {

	public static void main(String[] args) {
		
		// 사각형
		Rectangle r = new Rectangle("사각형", 3, 4);
		r.info();  // 도형타입 : 사각형 너비 3, 높이 4, 넓이 12
		
		// 정사각형
		Square s = new Square("정사각형", 5);
		s.info();  // 도형타입 : 정사각형 너비 5, 높이 5, 넓이 25
		
		// 삼각형
		Triangle t = new Triangle("삼각형", 3, 3);
		t.info();  // 도형타입 : 삼각형 너비 3, 높이 3, 넓이 4.5
		
		// 원
		Circle c = new Circle("원", 1.5);
		c.info();  // 도형타입 : 원 반지름 1.5, 넓이 x.xx

	}

}
