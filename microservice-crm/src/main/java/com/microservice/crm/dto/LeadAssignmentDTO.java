package com.microservice.crm.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LeadAssignmentDTO {
    private Long productId;
    private Long userId;
    private Long totalLeads;
    private String assignerName;
    private String productName;

    // Getters y Setters
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTotalLeads() {
        return totalLeads;
    }

    public void setTotalLeads(Long totalLeads) {
        this.totalLeads = totalLeads;
    }
}
