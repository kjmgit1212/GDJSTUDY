package com.goodee.ex14.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileAttachDTO {
	
	private Long fileAttachNo;
	private String path;
	private String origin;
	private String saved;
	private Long galleryNo;
	private Integer downloadCnt;
	
}
