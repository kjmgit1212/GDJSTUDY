package ex05_constructor;

public class Main {

	public static void main(String[] args) {

		Alba alba = new Alba("영수", "서울대", "도서관");
		
		alba.eat();
		alba.study();
		alba.work();
		
		System.out.println(alba.getName() + "가 " + alba.getSchool() + "학교를 다닌다.");
		
	}

}
