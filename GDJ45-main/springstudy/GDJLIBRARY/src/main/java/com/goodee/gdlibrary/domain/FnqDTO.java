package com.goodee.gdlibrary.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FnqDTO {
	   private Long fnqNo;
	   private String fnqTitle;
	   private String fnqContent;
}
