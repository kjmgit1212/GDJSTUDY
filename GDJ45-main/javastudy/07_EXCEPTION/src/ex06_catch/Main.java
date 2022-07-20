package ex06_catch;

public class Main {

	public static void m1() {
		try {
			int[] arr = new int[3];
			arr[0] = 1;
			arr[1] = 1;
			arr[2] = 1;
			arr[3] = 1;
		} catch(Exception e) {
			// 예외클래스 이름 알아내기
			String className = e.getClass().getName();
			System.out.println(className);
			// 예외메시지 알아내기
			String message = e.getMessage();
			System.out.println(message);
		}
	}
	
	public static void m2() {
		// 예외 메시지는 직접 만드는 것이 좋다.
		try {
			int score = -50;  // 0~100
			if(score < 0 || score > 100)
				throw new RuntimeException("점수는 0~100 사이 정수입니다.");
			System.out.println("점수는 " + score + "점입니다.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void m3() {
		try {
			Integer.parseInt("1.5");
		} catch (Exception e) {
			// 개발할 때 넣는 예외 처리 코드는 예외 확인 코드이다.
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		m3();
	}

}
