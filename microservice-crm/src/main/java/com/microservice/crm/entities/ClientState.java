package com.microservice.crm.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "client_states")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String slug;
}
