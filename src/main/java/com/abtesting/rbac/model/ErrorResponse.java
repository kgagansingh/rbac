package com.abtesting.rbac.model;

import lombok.Data;

@Data
public class ErrorResponse {
    private boolean error;
    private String code, message;

    public ErrorResponse(String code, String message) {
        this.error = true;
        this.code = code;
        this.message = message;
    }
}