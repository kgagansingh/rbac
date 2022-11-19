package com.abtesting.rbac.exceptions;

public class RetryableException extends BaseError {
    public RetryableException(StaticErrorsEnum err) {
        super(err.getStatusCode(), err.getErrorCode(), err.getDescription());
    }
}