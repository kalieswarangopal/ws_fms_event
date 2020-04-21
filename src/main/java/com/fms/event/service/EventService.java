package com.fms.event.service;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fms.event.dto.DashboardResponseDTO;
import com.fms.event.entity.Event;
import com.fms.event.helper.ExcelDownloadHelper;
import com.fms.event.repository.EmployeeRepository;
import com.fms.event.repository.EventRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EventService {

	@Autowired
	EventRepository eventRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	ExcelDownloadHelper excelDownloadHelper;

	public Flux<Event> getAllEvents() {

		return eventRepository.findAll();

	}

	public Mono<DashboardResponseDTO> getDashboardInfo() {

		Mono<Long> totalEvents = eventRepository.count();
		Mono<Integer> livesImpacted = eventRepository.getLivesImpacted();
		Mono<Integer> totalVolunteers = eventRepository.getTotalVolunteers();
		Mono<Long> totalParticipants = employeeRepository.count();

		return Mono.zip(totalEvents, livesImpacted, totalVolunteers, totalParticipants).flatMap(data -> {
			data.getT1();
			data.getT2();
			data.getT3();
			data.getT4();
			return Mono.just(new DashboardResponseDTO(data.getT1(), data.getT2(), data.getT3(), data.getT4()));
		});

	}

	public Mono<ResponseEntity> downloadExcel() {

		Mono<List<Event>> events = eventRepository.findAll().collectList();
		return events.map(event -> {
			ByteArrayInputStream stream = excelDownloadHelper.generateExcel(event);
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", "attachment; filename=Events.xlsx");
			headers.add("Content-Type", "application/octet-stream");
			return ResponseEntity.ok().headers(headers).body(new InputStreamResource(stream));
		});

	}

}
