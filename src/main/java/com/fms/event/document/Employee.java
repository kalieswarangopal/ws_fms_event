package com.fms.event.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

	private Integer employeeID;
	private String employeeName;
	private Integer volunteerHours;
	private Integer travelHours;
	private String businessUnit;
	private String iiepCategory;

}
