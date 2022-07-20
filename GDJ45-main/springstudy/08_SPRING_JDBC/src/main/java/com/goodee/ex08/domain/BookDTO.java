package com.goodee.ex08.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
	private Long book_no;
	private String title;
	private String author;
	private Integer price;
	private String pubDate;
	private String regDate;
}
