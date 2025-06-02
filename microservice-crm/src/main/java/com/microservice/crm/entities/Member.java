package com.microservice.crm.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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

    @Column(name = "employee_id", nullable = false, unique = true)
    private Long employeeId;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "crm_role", nullable = false)
    private String crmRole;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @Column(name = "status")
    private Integer statusCode;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Status getStatus() {
        return Status.fromCode(this.statusCode);
    }

    public void setStatus(Status status) {
        this.statusCode = status.getCode();
    }
}
