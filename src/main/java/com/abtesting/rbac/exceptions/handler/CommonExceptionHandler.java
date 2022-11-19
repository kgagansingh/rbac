package com.abtesting.rbac.exceptions.handler;

import com.abtesting.rbac.exceptions.BaseError;
import com.abtesting.rbac.exceptions.StaticErrorsEnum;
import com.abtesting.rbac.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@Slf4j
public class CommonExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> genericErrorHandler(Exception error, WebRequest request) {
        log.error(String.format("failed to process request with url :: %s,  error  :: %s", ((ServletWebRequest) request).getRequest().getRequestURI(), error));
        Integer statusCode = 500;
        String errorCode = StaticErrorsEnum.SOMETHING_WENT_WRONG.getErrorCode();
        String description = StaticErrorsEnum.SOMETHING_WENT_WRONG.getDescription();
        if (error instanceof BaseError) {
            statusCode = ((BaseError) error).getStatusCode();
            errorCode = ((BaseError) error).getErrorCode();
            description = ((BaseError) error).getDescription();
        }
        return new ResponseEntity<>(new ErrorResponse(errorCode, description), HttpStatus.valueOf(statusCode));
    }
}