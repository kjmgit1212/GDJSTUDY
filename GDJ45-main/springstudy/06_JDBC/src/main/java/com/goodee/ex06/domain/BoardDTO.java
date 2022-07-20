package com.goodee.ex06.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BoardDTO {
	private Long board_no;
	private String title;
	private String content;
	private String writer;
	private String created;
	private String lastModified;
}
