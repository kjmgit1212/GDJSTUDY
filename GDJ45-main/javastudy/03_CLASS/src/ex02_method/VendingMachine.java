package ex02_method;

public class VendingMachine {

	
	// 메소드
	// 1. 클래스가 가지고 있는 기능을 의미한다.
	// 2. 함수와 동일한 개념이다.
	// 3. 형식
	//    반환타입 메소드명(매개변수) {
	//        실행코드
	//        return 반환값
	//    }
	
	
	// 커피 뽑는 메소드
	// 1. 입력
	//    1) 돈
	//    2) 버튼(1 : 밀크커피, 2 : 블랙커피)
	// 2. 출력
	//    밀크커피 1잔
	//    블랙커피 2잔
	
	String getCoffee(int money, int button) {
		
		String res = "";
		
		if(button == 1)
			res += "밀크커피 ";
		else if(button == 2)
			res += "블랙커피 ";
		
		res += ((money / 500) + "잔");
		
		return res;
		
	}
	
}
