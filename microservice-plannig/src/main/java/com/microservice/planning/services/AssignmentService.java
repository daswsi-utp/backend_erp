package com.microservice.planning.services;
	
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.planning.entities.Participant;
import com.microservice.planning.entities.Plan;
import com.microservice.planning.entities.PlanParticipant;
import com.microservice.planning.entities.PlanParticipantId;
import com.microservice.planning.entities.PlanParticipantTask;
import com.microservice.planning.entities.PlanParticipantTaskId;
import com.microservice.planning.entities.Task;
import com.microservice.planning.repositories.ParticipantRepository;
import com.microservice.planning.repositories.PlanParticipantRepository;
import com.microservice.planning.repositories.PlanParticipantTaskRepository;
import com.microservice.planning.repositories.PlanRepository;
import com.microservice.planning.repositories.TaskRepository;

@Service
public class AssignmentService {

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private PlanParticipantRepository planParticipantRepository;

    @Autowired
    private PlanParticipantTaskRepository planParticipantTaskRepository;

    public void assignParticipantToPlan(int planId, int participantId) {
        Plan plan = planRepository.findById(planId).orElseThrow();
        Participant participant = participantRepository.findById(participantId).orElseThrow();

        PlanParticipantId ppId = new PlanParticipantId(planId, participantId);
        PlanParticipant planParticipant = new PlanParticipant(ppId, plan, participant);
        planParticipantRepository.save(planParticipant);
    }

    public void assignTaskToParticipantInPlan(int planId, int participantId, int taskId) {
        Plan plan = planRepository.findById(planId).orElseThrow();
        Participant participant = participantRepository.findById(participantId).orElseThrow();
        Task task = taskRepository.findById(taskId).orElseThrow();

        PlanParticipantTaskId pptId = new PlanParticipantTaskId(planId, participantId, taskId);
        PlanParticipantTask ppt = new PlanParticipantTask(pptId, plan, participant, task);
        planParticipantTaskRepository.save(ppt);
    }
    
    public List<Participant> getParticipantsByPlan(int planId) {        
        return planParticipantRepository.findAll()
        		.stream()
        		.filter( e-> e.getId().getPlanId() == planId)
        		.map(PlanParticipant::getParticipant)
        		.toList();
    }

    public List<Task> getTasksOfParticipantInPlan(int planId, int participantId) {
        return planParticipantTaskRepository.findAll()
        		.stream()
        		.filter(e -> e.getId().getPlanId()==planId)
        		.filter(e -> e.getId().getParticipantId()==participantId)
        		.map(PlanParticipantTask::getTask)
        		.toList();
    }
}
