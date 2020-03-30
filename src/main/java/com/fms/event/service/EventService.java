package com.fms.event.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.fms.event.dto.DashboardResponseDTO;
import com.fms.event.entity.Event;
import com.fms.event.repository.EmployeeRepository;
import com.fms.event.repository.EventRepository;

@Service
public class EventService {

	@Autowired
	EventRepository eventRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	public Flux<Event> getAllEvents() {

		return eventRepository.findAll();

	}

	public Mono<DashboardResponseDTO> getDashboardInfo() {

		Mono<Long> totalEvents = eventRepository.count();
		Mono<Integer> livesImpacted = eventRepository.getLivesImpacted();
		Mono<Integer> totalVolunteers = eventRepository.getTotalVolunteers();
		Mono<Long> totalParticipants = employeeRepository.count();

		return Mono.zip(totalEvents, livesImpacted, totalVolunteers,
				totalParticipants).flatMap(
				data -> {
					data.getT1();
					data.getT2();
					data.getT3();
					data.getT4();
					return Mono.just(new DashboardResponseDTO(data.getT1(),
							data.getT2(), data.getT3(), data.getT4()));
				});

	}

}
