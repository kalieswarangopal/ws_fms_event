package com.fms.event.document;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "Event")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {

	@Id
	private String id;
	private String eventID;
	private String month;
	private String baseLocation;
	private String councilName;
	private String beneficiaryName;
	private String eventName;
	private String eventDate;
	private String status;
	private String venueAddress;
	private Integer totalVolunteers;
	private Integer totalVolunteerHours;
	private Integer totalTravelHours;
	private String project;
	private String category;
	private String eventDescription;
	private Integer overallVolunteeringHours;
	private Integer livesImpacted;
	private Integer activityType;
	private String pocID;
	private String pocName;
	private String pocContactNumber;
	private List<Employee> employees;

}
