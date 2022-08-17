package com.example.springassignmentforum.core.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public final class ResponseNotFound  extends RuntimeException{
    public ResponseNotFound() {
        super();
    }

    public ResponseNotFound(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ResponseNotFound(final String message) {
        super(message);
    }

    public ResponseNotFound(final Throwable cause) {
        super(cause);
    }
}
