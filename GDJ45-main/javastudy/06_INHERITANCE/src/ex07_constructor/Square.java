package ex07_constructor;

// 정사각형은 사각형이다.
// Square is a Rectangle.

public class Square extends Rectangle {

	// field
	// 없다.

	// constructor
	public Square(int n) {
		super(n, n);
	}

	// method
	// Rectangle 클래스의 info 메소드를 그대로 사용한다.
	
}
