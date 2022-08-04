package com.example.springassignmentforum.web.handler;

import lombok.Data;

@Data
public class ResponseDataUtils<T> {
    private String message;
    private T data;
}
