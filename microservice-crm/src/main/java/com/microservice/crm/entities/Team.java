package com.microservice.crm.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "teams")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer status;

    // Lista de miembros asignados al equipo (asesores y coordinadores)
    @OneToMany(mappedBy = "team")
    private List<Member> members;

    // Coordinador del equipo (debe ser miembro con crmRole="coordinator_crm")
    @OneToOne
    @JoinColumn(name = "coordinator_member_id")
    private Member coordinator;
}

