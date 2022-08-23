package com.example.springassignmentforum.core.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public final class ResponseNotFound  extends RuntimeException{
    public String message;
    public Throwable throwable;
}
