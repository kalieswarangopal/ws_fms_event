package com.fms.event.service;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Service;

import com.fms.event.document.Event;
import com.fms.event.document.EventResponse;
import com.fms.event.repository.EventReactiveRepository;

import reactor.core.publisher.Flux;

@Service
public class EventService {

	@Autowired
	EventReactiveRepository eventReactiveRepository;

	@Autowired
	MongoTemplate mongoTemplate;

	public Flux<Event> getAllEvents() {

		return eventReactiveRepository.findAll();

	}

	public Flux<EventResponse> getEvents() {

		Aggregation agg = newAggregation(group("eventID").count().as("totalEvents").sum("livesImpacted")
				.as("livesImpacted").sum("totalVolunteers").as("totalVolunteers"));

		AggregationResults<EventResponse> groupResults = mongoTemplate.aggregate(agg, Event.class, EventResponse.class);
		List<EventResponse> result = groupResults.getMappedResults();
		return Flux.fromIterable(result);
	}

}
