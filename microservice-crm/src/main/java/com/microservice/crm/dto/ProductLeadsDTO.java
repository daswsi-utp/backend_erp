package com.microservice.crm.dto;

public class ProductLeadsDTO {
	
	private Long productId; 
    private String productName;
    private Long totalLeads;
    private Long availableLeads;

    public ProductLeadsDTO(Long productId, String productName, Long totalLeads, Long availableLeads) {
        this.productId = productId;
        this.productName = productName;
        this.totalLeads = totalLeads;
        this.availableLeads = availableLeads;
    }
    
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getTotalLeads() {
        return totalLeads;
    }

    public void setTotalLeads(Long totalLeads) {
        this.totalLeads = totalLeads;
    }

    public Long getAvailableLeads() {
        return availableLeads;
    }

    public void setAvailableLeads(Long availableLeads) {
        this.availableLeads = availableLeads;
    }
}
