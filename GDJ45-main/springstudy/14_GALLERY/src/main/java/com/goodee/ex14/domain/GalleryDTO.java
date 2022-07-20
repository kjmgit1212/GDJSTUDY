package com.goodee.ex14.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GalleryDTO {
	private Long galleryNo;
	private String writer;
	private String title;
	private String content;
	private String ip;
	private Long hit;
	private Date created;
	private Date modified;
}
