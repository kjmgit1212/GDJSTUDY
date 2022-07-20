package com.goodee.ex18.domain;

// MySQL의 Datetime 타입과 호환되는 java.util.Date 사용
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeDTO {

	private Long noticeNo;
	private MemberDTO member;
	private String title;
	private String content;
	private Date createdDate;
	private Date modifiedDate;
	
}
