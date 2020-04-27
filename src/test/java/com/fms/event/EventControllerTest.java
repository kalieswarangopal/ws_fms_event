package com.fms.event;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.fms.event.controller.EventController;
import com.fms.event.dto.DashboardResponseDTO;
import com.fms.event.entity.Event;
import com.fms.event.helper.ExcelDownloadHelper;
import com.fms.event.repository.EmployeeRepository;
import com.fms.event.repository.EventRepository;
import com.fms.event.service.EventService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = EventController.class)
@Import(EventService.class)
public class EventControllerTest {

	@Autowired
	WebTestClient webTestClient;

	@MockBean
	EventRepository eventRepository;

	@MockBean
	EmployeeRepository employeeRepository;

	@MockBean
	ExcelDownloadHelper excelDownloadHelper;

	@Test
	public void testGetAllEvents() {

		Event event = new Event();
		event.setEventID("EVN123");
		event.setEventName("Event name");
		List<Event> eventList = new ArrayList<>();
		eventList.add(event);
		Flux<Event> eventFlux = Flux.fromIterable(eventList);

		Mockito.when(eventRepository.findAll()).thenReturn(eventFlux);

		webTestClient.get().uri("/events").header(HttpHeaders.ACCEPT, "application/json").exchange().expectStatus()
				.isOk().expectBodyList(Event.class);

		Mockito.verify(eventRepository, times(1)).findAll();
	}

	@Test
	public void testGetDashboardInfo() {

		Mockito.when(eventRepository.count()).thenReturn(Mono.just(3L));
		Mockito.when(employeeRepository.count()).thenReturn(Mono.just(3L));
		Mockito.when(eventRepository.getLivesImpacted()).thenReturn(Mono.just(30));
		Mockito.when(eventRepository.getTotalVolunteers()).thenReturn(Mono.just(23));

		webTestClient.get().uri("/dashboard").header(HttpHeaders.ACCEPT, "application/json").exchange().expectStatus()
				.isOk().expectBody(DashboardResponseDTO.class)
				.value(response -> response.getLivesImpacted(), equalTo(30));

		Mockito.verify(eventRepository, times(1)).count();
		Mockito.verify(eventRepository, times(1)).getTotalVolunteers();
		Mockito.verify(eventRepository, times(1)).getLivesImpacted();
		Mockito.verify(employeeRepository, times(1)).count();

	}

}
