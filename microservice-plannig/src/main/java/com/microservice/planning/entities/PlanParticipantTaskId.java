package com.microservice.planning.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class PlanParticipantTaskId implements Serializable {

    @Column(name = "plan_id")
    private int planId;

    @Column(name = "participant_id")
    private int participantId;

    @Column(name = "task_id")
    private int taskId;
}
