package ex02_reference_array;

public class Ex02_advanced_for {

	public static void main(String[] args) {
		
		// 향상 for
		// 1. 인덱스 없이 사용한다.
		// 2. 형식
		//    for(변수 : 배열) { }

		String[] menu = {"김치찌개", "된장찌개", "부대찌개"};
		
		for(String food : menu)
			System.out.println(food);
		
	}

}
