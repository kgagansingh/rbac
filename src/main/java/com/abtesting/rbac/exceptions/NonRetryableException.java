package com.abtesting.rbac.exceptions;

public class NonRetryableException extends BaseError {
    public NonRetryableException(StaticErrorsEnum err) {
        super(err.getStatusCode(), err.getErrorCode(), err.getDescription());
    }

    public NonRetryableException(DynamicErrors err) {
        super(err.getStatusCode(), err.getErrorCode(), err.getDescription());
    }
}