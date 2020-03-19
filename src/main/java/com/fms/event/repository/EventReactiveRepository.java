package com.fms.event.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.fms.event.document.Event;

public interface EventReactiveRepository extends ReactiveMongoRepository<Event, String>{

}
