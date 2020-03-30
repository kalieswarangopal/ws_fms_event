package com.fms.event.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("event")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {
	
	@Id
	@Column("EventID")
	private String eventID;
	
	@Column("EventName")
	private String eventName;
	
	@Column("Month")
	private String month;
	
	@Column("BaseLocation")
	private String baseLocation;
	
	@Column("CouncilName")
	private String councilName;
	
	@Column("BeneficiaryName")
	private String beneficiaryName;
	
	@Column("Status")
	private String status;
	
	@Column("VenueAddress")
	private String venueAddress;
	
	@Column("EventDate")
	private String eventDate;
	
	@Column("TotalVolunteers")
	private Integer totalVolunteers;
	
	@Column("TotalVolunteerHours")
	private Integer totalVolunteerHours;
	
	@Column("TotalTravelHours")
	private Integer totalTravelHours;
	
	@Column("Project")
	private String project;
	
	@Column("Category")
	private String category;
	
	@Column("EventDescription")
	private String eventDescription;
	
	@Column("OverallVolunteeringHours")
	private Integer overallVolunteeringHours;
	
	@Column("LivesImpacted")
	private Integer livesImpacted;
	
	@Column("ActivityType")
	private Integer activityType;
	

}
