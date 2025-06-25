package com.microservice.crm.dto;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
    private Long id;
    private String name;
    private Double pricePen;
    private Double priceDollar;
    private LocalDate start;
    private String code;
    private Integer status;
    private String cover;
    private String description;
}
