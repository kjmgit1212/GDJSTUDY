package ex01_branch;

public class Ex01_if {

	public static void main(String[] args) {
		
		int score = 0;
		
		if(score >= 60)
			System.out.println("합격");
		
		if(score >= 60) {
			System.out.println("합격");
			System.out.println("축하합니다");
		}

		// 문제. 남자는 왼쪽, 여자는 오른쪽
		String gender = "남자";
		
		if(gender.equals("남자"))
			System.out.println("왼쪽");
		
		if(gender.equals("여자"))
			System.out.println("오른쪽");
		
	}

}
