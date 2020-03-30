package com.fms.event.repository;

import org.springframework.data.r2dbc.repository.query.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Mono;

import com.fms.event.entity.Event;

@Repository
public interface EventRepository extends ReactiveCrudRepository<Event, String> {
	
	@Query("Select sum(livesimpacted) from event")
	Mono<Integer> getLivesImpacted();
	
	@Query("Select sum(totalvolunteers) from event")
	Mono<Integer> getTotalVolunteers();
	

}
