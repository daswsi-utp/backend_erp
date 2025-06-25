package com.microservice.crm.entities;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column
    private Double pricePen;
    
    @Column
    private Double priceDollar;
    
    @Column(nullable = false)
    private LocalDate start;
    
    @Column(nullable = false)
    private String code;
    
    private Integer status;
    
    private String cover;
    
    @Column(columnDefinition = "TEXT")
    private String description;
}
