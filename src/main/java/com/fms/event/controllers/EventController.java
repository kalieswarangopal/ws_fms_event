package com.fms.event.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fms.event.document.Event;
import com.fms.event.document.EventResponse;
import com.fms.event.service.EventService;

import reactor.core.publisher.Flux;

@RestController
public class EventController {

	@Autowired
	EventService eventService;

	@GetMapping(value = "/eventlist", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Flux<Event> getAllEvents() {

		return eventService.getAllEvents();
	}
	
	@GetMapping(value = "/events", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Flux<EventResponse> getEvents() {

		return eventService.getEvents();
	}

}
