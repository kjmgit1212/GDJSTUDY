package dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book {
	private long no;
	private String title;
	private String link;
	private String image;
	private String author;
	private int price;
	private int discount;
	private String publisher;
	private String isbn;
	private String description;
	private String pubdate;
}
