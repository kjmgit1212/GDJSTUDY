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
public class RentDTO {
	   private Long rentNo;
	   private Long bookNo;
	   private String memberId;
	   private Date rentDate;
	   private Date rentExpirationDate;

}
