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
public class ReplyDTO {
	private Long replyNo;
	private String writer;
	private String content;
	private String ip;
	private Long boardNo;
	private Date created;
}
