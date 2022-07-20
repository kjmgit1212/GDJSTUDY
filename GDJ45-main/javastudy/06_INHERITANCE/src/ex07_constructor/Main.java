package ex07_constructor;

public class Main {

	public static void main(String[] args) {
		
		// 사각형
		Rectangle rectangle = new Rectangle(3, 4);
		rectangle.info();  // 너비 3, 높이 4, 넓이 12
		
		// 정사각형
		Square square = new Square(3);
		square.info();     // 너비 3, 높이 3, 넓이 9
		
	}
	
}
