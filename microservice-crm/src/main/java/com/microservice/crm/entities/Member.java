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

    @Column(name = "employee_id", nullable = false, unique = true)
    private Long employeeId;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "crm_role")
    private String crmRole;  
    
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @Column(name = "status")
    private Integer status; 
}

