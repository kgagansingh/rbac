package com.abtesting.rbac.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class BaseError extends RuntimeException {
    private Integer statusCode;
    private String errorCode;
    private String description;
}