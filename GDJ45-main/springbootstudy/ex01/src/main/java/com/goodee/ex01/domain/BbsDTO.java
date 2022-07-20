package com.goodee.ex01.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BbsDTO {
	private Long bbsNo;
	private String writer;
	private String title;
	private String content;
	private String created;
	private String modified;
}
