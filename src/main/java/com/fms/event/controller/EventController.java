package com.fms.event.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.fms.event.dto.DashboardResponseDTO;
import com.fms.event.entity.Event;
import com.fms.event.service.EventService;

@RestController
public class EventController {

	@Autowired
	EventService eventService;

	@GetMapping("/events")
	public Flux<Event> getAllEvents() {

		return eventService.getAllEvents();
	}

	@GetMapping("/dashboard")
	public Mono<DashboardResponseDTO> getDashboardInfo() {
		
		return eventService.getDashboardInfo();
	}
	
}
