package com.microservice.planning.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.planning.entities.Task;

public interface TaskRepository extends JpaRepository<Task, Integer>{

}
