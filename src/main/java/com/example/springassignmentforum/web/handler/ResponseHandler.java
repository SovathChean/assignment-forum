package com.example.springassignmentforum.web.handler;

import com.example.springassignmentforum.core.common.filter.PageFilterResult;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponseHandler {

    public static <T> ResponseEntity<ResponseListDataUtils<T>> responseListData(String message, HttpStatus status , List<T> responseObj)
    {
        ResponseListDataUtils<T> response = new ResponseListDataUtils();
        message = (message != null) ? message : "Request successfully";
        response.setData(responseObj);
        response.setMessage(message);

        return new ResponseEntity<ResponseListDataUtils<T>>(response, status);
    }
    public static <T> ResponseEntity<ResponseDataUtils<T>> responseData(String message, HttpStatus status , T responseObj)
    {
        ResponseDataUtils<T> response = new ResponseDataUtils();
        response.setData(responseObj);

        return new ResponseEntity<ResponseDataUtils<T>>(response, status);
    }
    public static <T> ResponseEntity<ResponsePageUtils<T>> responsePagination(String message, HttpStatus status , PageFilterResult<T> responseObj)
    {
        ResponsePageUtils<T> response = new ResponsePageUtils();
        message = (message != null) ? message : "Request successfully";
        response.setData(responseObj.getPageData());
        response.setTotalRows(responseObj.getTotalRows());
        response.setMessage(message);

        return new ResponseEntity<ResponsePageUtils<T>>(response, status);
    }
    public static ResponseEntity<Object> responseWithMsg(String message, HttpStatus status)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        message = (message != null) ? message : "Request successfully";
        map.put("message", message);


        return new ResponseEntity<Object>(map, status);
    }
    public static ResponseEntity<Object> responseErrorWithMsg(String message, HttpStatus status)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", message);

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
