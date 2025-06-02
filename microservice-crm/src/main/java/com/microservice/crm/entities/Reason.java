package com.microservice.crm.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "reasons")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reason {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String slug;
}
