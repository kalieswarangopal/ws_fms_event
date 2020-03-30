package com.fms.event.handler;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

import com.fms.event.entity.Event;
import com.fms.event.repository.EventRepository;

@Component
public class EventHandler {

	@Autowired
	EventRepository eventRepository;

	public Mono<ServerResponse> getEvent(ServerRequest serverRequest) {

		String id = serverRequest.pathVariable("id");
		Mono<Event> event = eventRepository.findById(id);

		return event.flatMap(ev -> ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON).body(fromObject(ev)));
	}

}
