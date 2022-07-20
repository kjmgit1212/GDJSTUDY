package ex06_static;

public class CalculatorMain {

	public static void main(String[] args) {
		
		/*
		Calculator calc1 = new Calculator();
		calc1.add(1, 2);
		
		Calculator calc2 = new Calculator();
		calc2.add(1, 2);
		*/
		
		// 인스턴스를 생성하지 않고 클래스로 곧바로 호출하는 클래스 메소드
		
		Calculator.add(1, 2);
		Calculator.sub(2, 4);
		Calculator.mul(3, 4);
		Calculator.div(4, 2);
		Calculator.mod(5, 2);
		Calculator.pow(2, 3);
		Calculator.abs(-5);
		
	}

}