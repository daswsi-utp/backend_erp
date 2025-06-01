package com.microservice.crm.entities;

public enum Status {
    INACTIVE(0),
    ACTIVE(1);

    private final int code;

    Status(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static Status fromCode(int code) {
        for (Status s : values()) {
            if (s.code == code) return s;
        }
        throw new IllegalArgumentException("Invalid Status code: " + code);
    }
}
