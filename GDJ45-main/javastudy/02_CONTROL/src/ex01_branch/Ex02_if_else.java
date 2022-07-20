package ex01_branch;

public class Ex02_if_else {

	public static void main(String[] args) {
		
		int score = 100;
		
		if (score >= 60)
			System.out.println("합격");
		else
			System.out.println("불합격");
		
		if (score >= 60) {
			System.out.println("합격");
			System.out.println("축하합니다.");
		} else {
			System.out.println("불합격");
			System.out.println("재시험입니다.");
		}
		
		
		// 문제. 남자는 왼쪽, 여자는 오른쪽
		// 제한조건 : 남자, 여자 이외는 없음
		
		String gender = "남자";
		
		if (gender.equals("남자"))
			System.out.println("왼쪽");
		else
			System.out.println("오른쪽");

	}

}
