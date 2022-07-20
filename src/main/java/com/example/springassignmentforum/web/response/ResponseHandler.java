package com.example.springassignmentforum.web.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {


    public static ResponseEntity<Object> responseWithObject(String message, HttpStatus status ,Object responseObj)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        message = message != null? message : "Request successfully";
        map.put("message", message);
        map.put("status", status.values());
        map.put("data", responseObj);

        return new ResponseEntity<Object>(map, status);
    }
    public static ResponseEntity<Object> responseWithMsg(String message, HttpStatus status)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", message);
        map.put("status", status.values());

        return new ResponseEntity<Object>(map, status);
    }

}
