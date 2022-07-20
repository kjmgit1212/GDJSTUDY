package ex18_interface;

public class Main {

	public static void main(String[] args) {
		
		Shape shape1 = new Rectangle(3, 4);
		shape1.info();
		shape1.message();
		
		Shape shape2 = new Circle(1.5);
		shape2.info();
		shape2.message();

	}

}
