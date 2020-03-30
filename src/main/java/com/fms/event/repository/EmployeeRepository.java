package com.fms.event.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.fms.event.entity.Employee;

public interface EmployeeRepository extends ReactiveCrudRepository<Employee, Integer>{

}
