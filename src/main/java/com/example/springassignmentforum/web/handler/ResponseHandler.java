package com.example.springassignmentforum.web.handler;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {


    public static ResponseEntity<Object> responseWithObject(String message, HttpStatus status ,Object responseObj)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        message = message != null? message : "Request successfully";
        map.put("message", message);
        map.put("data", responseObj);
        map.put("success", true);

        return new ResponseEntity<Object>(map, status);
    }
    public static ResponseEntity<Object> responseWithMsg(String message, HttpStatus status)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", message);
        map.put("success", true);

        return new ResponseEntity<Object>(map, status);
    }
    public static ResponseEntity<Object> responseErrorWithObject(String message, HttpStatus status ,Object responseObj)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        message = message != null? message : "Request Fail.";
        map.put("message", message);
        map.put("data", responseObj);
        map.put("success", false);

        return new ResponseEntity<Object>(map, status);
    }
    public static ResponseEntity<Object> responseErrorWithMsg(String message, HttpStatus status)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", message);
        map.put("success", false);

        return new ResponseEntity<Object>(map, status);
    }

    public static ResponseEntity<Object> responseDownload(String folderPath, String fileName) throws FileNotFoundException {

        File file = new File(folderPath);
        HttpHeaders headers = new HttpHeaders(); headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;  filename=\"" + fileName + "\"");
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

}
