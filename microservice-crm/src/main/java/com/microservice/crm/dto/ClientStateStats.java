package com.microservice.crm.dto;

public class ClientStateStats {

    private String clientState;  
    private Long callsCount;     
    private Long uniqueClients; 
    private String clientStateSlug; 
    private Long clientStateId; 

    // Constructor
    public ClientStateStats(String clientState, Long callsCount, Long uniqueClients, String clientStateSlug, Long clientStateId) {
        this.clientState = clientState;
        this.callsCount = callsCount;
        this.uniqueClients = uniqueClients;
        this.clientStateSlug = clientStateSlug;
        this.clientStateId = clientStateId;
    }

    // Getters y Setters
    public String getClientState() {
        return clientState;
    }

    public void setClientState(String clientState) {
        this.clientState = clientState;
    }

    public Long getCallsCount() {
        return callsCount;
    }

    public void setCallsCount(Long callsCount) {
        this.callsCount = callsCount;
    }

    public Long getUniqueClients() {
        return uniqueClients;
    }

    public void setUniqueClients(Long uniqueClients) {
        this.uniqueClients = uniqueClients;
    }

    public String getClientStateSlug() {
        return clientStateSlug;
    }

    public void setClientStateSlug(String clientStateSlug) {
        this.clientStateSlug = clientStateSlug;
    }

    public Long getClientStateId() {
        return clientStateId;
    }

    public void setClientStateId(Long clientStateId) {
        this.clientStateId = clientStateId;
    }
}

