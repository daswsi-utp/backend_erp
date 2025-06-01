package com.microservice.crm.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "arrival_means")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArrivalMean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String slug;
}