package ex04_set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Book {
	
	private String isbn;
	private String title;
	
	// hashCode() 메소드
	// Object 클래스 : 인스턴스의 참조값
	// Book 클래스   : 제목의 글자 수 (예시 중 하나일 뿐)
	
	@Override
	public int hashCode() {
		return title.length();
	}
	
	// equals() 메소드
	// Object 클래스 : 인스턴스의 참조값 비교
	// Book 클래스   : isbn 비교
	@Override
	public boolean equals(Object obj) {
		Book book = (Book)obj;
		return isbn.equals(book.isbn);
	}
	
}
