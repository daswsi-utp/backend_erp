package com.microservice.planning.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.planning.entities.Task;
import com.microservice.planning.repositories.TaskRepository;

@Service
public class TaskService {
	
	@Autowired
	private TaskRepository taskRepository;
	
	public List<Task> getTasks() {
		return taskRepository.findAll();
	}
	
	public Task createTask(Task task) {
		return taskRepository.save(task);
	}
	
	public Task updateTask(Task task) {
		return taskRepository.save(task);
	}
	
	public void deleteTask(Integer id) {
		taskRepository.deleteById(id);
	}
	
}
