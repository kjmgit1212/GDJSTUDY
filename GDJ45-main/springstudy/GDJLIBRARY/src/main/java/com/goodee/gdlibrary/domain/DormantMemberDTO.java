package com.goodee.gdlibrary.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DormantMemberDTO {

	private Long dormantNo;
	private Long memberNo;
	private String memberId;
	private String memberPw;
	private String memberName;
	private String memberPhone;
	private String memberEmail;
	private String memberPostcode;
	private String memberRoadAddress;
	private String memberDetailAddress;
	private Integer memberAgreeState;
	private Date memberSignUp;
	private String memberSnsType;
	private Date dormantDate;
	private Date memberPwModified;
	private Date memberInfoModified;



}
