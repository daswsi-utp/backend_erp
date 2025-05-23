package com.microservice.sales.dto;

import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class DetailQuoteDTO {
	
	 	private int amount;
	    private double prize;
	    private double discount;
	    private double tax;

}
