package ex05_throws;

public class Main {

	// throws NumberFormatException
	// m1 메소드는 NumberFormatException을 발생시킨다.
	public static void m1() throws NumberFormatException {
		String strAge = "20";
		int age = Integer.parseInt(strAge);
		System.out.println("입력된 나이는 " + age + "살입니다.");
	}
	
	public static void m2() throws NumberFormatException {
		String strHeight = "180.5";
		double height = Double.parseDouble(strHeight);
		System.out.println("입력된 키는 " + height + "cm입니다.");
	}
	
	public static void main(String[] args) {
		try {
			m1();
			m2();
		} catch (NumberFormatException e) {
			System.out.println("예외가 발생했습니다.");
		}
	}

}
