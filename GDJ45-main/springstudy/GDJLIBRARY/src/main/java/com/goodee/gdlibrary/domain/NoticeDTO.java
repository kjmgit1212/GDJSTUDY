package com.goodee.gdlibrary.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoticeDTO {
	   // sql에서 Notice 목록을 가져올 때 ROW_NUM로 순서를 받아와서 
	   // 받아줄 변수가 필요해서
	   // private Long rowNum; 추가함!
	   // Notice 테이블에 칼럼은 추가하지 않음! 추가할 필요도 없고
	   // private Long rowNum;
	   private Long noticeNo;
	   private String noticeTitle;
	   private String noticeContent;
	   private Date noticeCreated;
	   private Date noticeModified;
	   private String noticeIp;
	   private Integer noticeHit;

}
