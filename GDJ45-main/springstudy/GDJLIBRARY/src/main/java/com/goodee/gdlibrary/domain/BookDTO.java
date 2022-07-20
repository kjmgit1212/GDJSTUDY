package com.goodee.gdlibrary.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDTO {

		private Long bookNo;
		private String bookIsbn;
		private String bookTitle;
		private String bookAuthor;
		private String bookPublisher;
		private String bookPubdateTime;
		private String bookDescription;
		private String bookImage;
		private String bookType;

}
