package com.microservice.sales.dto;

import java.sql.Timestamp;
import java.util.List;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class QuoteRequestDTO {
	
	private Long clientId;
    private Long employeeId;
    private String typePayment;
    private String observation;
    private Timestamp issueDate;
    private Timestamp expirationDate;
    private List<DetailQuoteDTO> details;

}
