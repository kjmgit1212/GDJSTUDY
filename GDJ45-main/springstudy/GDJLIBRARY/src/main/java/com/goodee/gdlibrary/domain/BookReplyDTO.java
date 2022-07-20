package com.goodee.gdlibrary.domain;



import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
public class BookReplyDTO {
	private Long bookReplyNo;
	private Long bookNo;
	private String memberId;
	private String bookReplyContent;
	private Date bookReplyCreated;
	private Integer bookRating;
}
