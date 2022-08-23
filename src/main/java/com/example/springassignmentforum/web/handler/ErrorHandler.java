package com.example.springassignmentforum.web.handler;

import com.example.springassignmentforum.core.common.exception.ResponseNotFound;
import com.example.springassignmentforum.web.vo.response.ErrorResponseVO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value
            = {ResponseNotFound.class})
    protected ResponseEntity<Object> handleNotFound(
            RuntimeException ex, WebRequest request) {
        ErrorResponseVO error = new ErrorResponseVO();
        String bodyOfResponse = ex.getMessage();
        error.setError(bodyOfResponse);
        error.setSuccess(false);
        return handleExceptionInternal(ex, error,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }


}
