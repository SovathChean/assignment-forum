package com.example.springassignmentforum.web.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDataUtils<T> {
    private String message;
    private T data;
}
