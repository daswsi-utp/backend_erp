package com.microservice.crm.entities;

public enum TeamStatus {
    INACTIVE(0),
    ACTIVE(1);

    private final int code;

    TeamStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static TeamStatus fromCode(int code) {
        for (TeamStatus status : TeamStatus.values()) {
            if (status.code == code) return status;
        }
        throw new IllegalArgumentException("Invalid code for TeamStatus: " + code);
    }
}

