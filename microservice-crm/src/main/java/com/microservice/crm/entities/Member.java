package com.microservice.crm.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "members")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Referencia al empleado en RRHH
    @Column(name = "employee_id", nullable = false, unique = true)
    private Long employeeId;

    // Nombre completo para mostrar en CRM (ejemplo: "Juan Pérez")
    @Column(name = "full_name", nullable = false)
    private String fullName;

    // Rol dentro del CRM: "admin_crm", "coordinator_crm", "asesor_crm"
    @Column(name = "crm_role", nullable = false)
    private String crmRole;

    // Equipo al que pertenece el miembro (asesor o coordinador)
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;


    @Column(name = "status")
    private Integer statusCode;

    public Status getStatus() {
        return Status.fromCode(this.statusCode);
    }

    public void setStatus(Status status) {
        this.statusCode = status.getCode();
    }
}
