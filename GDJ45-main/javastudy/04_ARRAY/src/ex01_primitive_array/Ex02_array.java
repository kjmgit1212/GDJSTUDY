package ex01_primitive_array;

public class Ex02_array {

	public static void main(String[] args) {
		
		// 배열의 초기화
		int[] arr1 = new int[] {10, 20, 30, 40, 50};
		int[] arr2 = {100, 200, 300, 400, 500};
		
		for(int i = 0; i < 5; i++)
			System.out.println(arr1[i]);
		
		for(int i = 0; i < 5; i++)
			System.out.println(arr2[i]);

	}

}
