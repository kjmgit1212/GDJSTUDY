package com.goodee.ex11.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {

	private Integer departmentId;
	private String departmentName;
	private Integer managerId;
	private Integer locationId;
	
}