package com.fms.event.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("employee")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
	
	@Id
	@Column("EmployeeID")
	private Integer employeeID;
	
	@Column("EmployeeName")
	private String employeeName;
	
	@Column("VolunteerHours")
	private Integer volunteerHours;
	
	@Column("TravelHours")
	private Integer TravelHours;
	
	@Column("BusinessUnit")
	private String businessUnit;
	
	@Column("IIEPCategory")
	private String iiepCategory;
	
	@Column("EventID")
	private String eventID;

}
