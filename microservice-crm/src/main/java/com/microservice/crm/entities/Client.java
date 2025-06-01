package com.microservice.crm.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String whatsapp;

    private String country;

    private String countryCode;

    private String city;

    private String address;

    private String companyName;

    private String jobTitle;

    @Enumerated(EnumType.STRING)
    @Column(name = "client_status")
    private ClientStatus clientStatus;

    @ManyToOne
    @JoinColumn(name = "assigned_team_id")
    private Team assignedTeam;

    @ManyToOne
    @JoinColumn(name = "assigned_member_id")
    private Member assignedMember;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
