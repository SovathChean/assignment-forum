package com.example.springassignmentforum.web.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseListDataUtils<T>{
    private List<T> data;
    private String message;

}
