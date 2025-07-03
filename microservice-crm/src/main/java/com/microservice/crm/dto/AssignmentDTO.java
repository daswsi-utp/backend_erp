package com.microservice.crm.dto;

public class AssignmentDTO {

    private Long user_id;  
    private int total_assign;  

    // Getters y Setters

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public int getTotal_assign() {
        return total_assign;
    }

    public void setTotal_assign(int total_assign) {
        this.total_assign = total_assign;
    }
}
