package ex04_set;

import java.util.HashSet;
import java.util.Set;

public class Desk {

	// 책상에 동일한 책은 1권만 둔다.
	private Set<Book> books;
	
	public Desk() {
		books = new HashSet<Book>();
	}
	
	public void addBook(Book book) {
		books.add(book);
	}
	
	public void removeBook(Book book) {
		if(books.contains(book))  // 포함되어 있다면,
			books.remove(book);   // 제거
	}
	
	public void findAllBooks() {
		for(Book book : books)
			System.out.println(book);
	}
	
}
