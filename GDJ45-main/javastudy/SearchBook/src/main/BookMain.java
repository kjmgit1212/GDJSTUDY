package main;

import service.BookService;

public class BookMain {

	public static void main(String[] args) {
		BookService service = new BookService();
		service.searchBook();
	}

}
