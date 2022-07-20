package ex02_loop;

public class Quiz02 {

	public static void main(String[] args) {
		
		// 2x1=2   3x1=3   4x1=4   ...   9x1=9
		// 2x2=4   3x2=6   4x3=12  ...   9x2=18
		// ...
		
		for(int n = 1; n <= 9; n++) {
			for(int dan = 2; dan <= 9; dan++)
				System.out.print(dan + "x" + n + "=" + (dan * n) + "\t");  // \t : 탭 문자
			System.out.println();  // 줄 바꿈
		}
		
	}

}
