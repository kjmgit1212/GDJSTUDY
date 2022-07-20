package ex04_set;

public class Main {

	public static void main(String[] args) {
		
		Desk desk = new Desk();
		
		// 추가1. 동일한 인스턴스의 경우
		// 인스턴스의 참조값(저장된 위치)이 동일하기 때문에 중복 검사 가능
		Book book = new Book("1234", "어린왕자");
		desk.addBook(book);
		desk.addBook(book);
		
		// 추가2. 다른 인스턴스의 경우(하지만 내용은 같은)
		// 인스턴스의 참조값(저장된 위치)이 다르기 때문에 중복 검사가 안 됨
		// => 인스턴스의 내용을 비교해서 중복 검사할 수 있도록
		//    Book 클래스에 hashCode() & equals() 메소드를 오버라이드 해야 함
		desk.addBook(new Book("5678", "소나기"));
		desk.addBook(new Book("5678", "소나기"));
		
		// 삭제
		desk.removeBook(new Book("1234", "어린왕자"));
				
		desk.findAllBooks();

	}

}
