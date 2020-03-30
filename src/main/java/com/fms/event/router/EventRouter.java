package com.fms.event.router;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.fms.event.handler.EventHandler;

@Configuration
public class EventRouter {

	@Bean
	public RouterFunction<ServerResponse> eventRoute(EventHandler eventHandler) {

		return RouterFunctions.route(
				GET("/event/{id}").and(accept(MediaType.APPLICATION_JSON)),
				eventHandler::getEvent);
	}

}
