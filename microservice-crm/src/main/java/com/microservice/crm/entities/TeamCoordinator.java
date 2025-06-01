package com.microservice.crm.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "team_coordinators")
public class TeamCoordinator {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member coordinator; 

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    private LocalDateTime dateAssigned;

    private Integer status;
}
