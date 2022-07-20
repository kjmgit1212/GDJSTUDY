package com.goodee.ex12.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDTO {
	private Long rn;
	private Long boardNo;
	private String writer;
	private String title;
	private String content;
	private Long hit;
	private String ip;
	private Date created;
	private Date modified;
}
