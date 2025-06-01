package com.microservice.planning.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.planning.entities.Task;
import com.microservice.planning.services.TaskService;

@RestController
@RequestMapping("/api/planning/task")
public class TaskController {

	@Autowired
	private TaskService taskService;
	
	@GetMapping
	public List<Task> listTasks() {
		return taskService.getTasks();
	}

	@PostMapping("create")
	public Task create(@RequestBody Task task) {
		return taskService.createTask(task);
	}
	
	@PutMapping("update/{id}")
	public Task update(@RequestBody Task task, @PathVariable Integer id) {
		task.setTask_id(id);
		return taskService.updateTask(task);
	}
	
	@DeleteMapping("delete/{id}")
	public void delete(@PathVariable Integer id) {
		taskService.deleteTask(id);
	}
	
}
