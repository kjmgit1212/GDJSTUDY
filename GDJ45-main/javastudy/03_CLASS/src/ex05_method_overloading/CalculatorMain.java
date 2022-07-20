package ex05_method_overloading;

public class CalculatorMain {

	public static void main(String[] args) {
		
		Calculator calc = new Calculator();
		
		calc.add(15, 16);
		calc.add(10, 20, 30);
		calc.add(1.5, 1.2);

	}

}
