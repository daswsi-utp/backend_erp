package com.microservice.planning.controllers;

import com.microservice.planning.entities.Participant;
import com.microservice.planning.entities.Task;
import com.microservice.planning.services.AssignmentService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {

    @Autowired
    private AssignmentService assignmentService;

    @PostMapping("/plan/{planId}/participant/{participantId}")
    public String assignParticipantToPlan(
        @PathVariable int planId,
        @PathVariable int participantId
    ) {
        assignmentService.assignParticipantToPlan(planId, participantId);
        return "Participant assigned to plan.";
    }

    @PostMapping("/plan/{planId}/participant/{participantId}/task/{taskId}")
    public String assignTaskToParticipantInPlan(
        @PathVariable int planId,
        @PathVariable int participantId,
        @PathVariable int taskId
    ) {
        assignmentService.assignTaskToParticipantInPlan(planId, participantId, taskId);
        return "Task assigned to participant in plan.";
    }
    
    @GetMapping("/plan/{planId}/participants")
    public List<Participant> getParticipantsByPlan(@PathVariable int planId) {
        return assignmentService.getParticipantsByPlan(planId);
    }

    @GetMapping("/plan/{planId}/participant/{participantId}/tasks")
    public List<Task> getTasksOfParticipantInPlan(
        @PathVariable int planId,
        @PathVariable int participantId
    ) {
        return assignmentService.getTasksOfParticipantInPlan(planId, participantId);
    }
}
