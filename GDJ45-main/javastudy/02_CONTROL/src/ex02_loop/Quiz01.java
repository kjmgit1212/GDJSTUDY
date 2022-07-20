package ex02_loop;

public class Quiz01 {

	public static void main(String[] args) {
		
		// 평점에 따른 별(★) 출력하기
		
		int star = 5;
		String res = "";
		
		for(int n = 1; n <= star; n++)
			res += "★";
		
		System.out.println(res);
		
	}

}
