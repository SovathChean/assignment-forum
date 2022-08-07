package com.example.springassignmentforum.helper;

import com.example.springassignmentforum.web.handler.ResponseDataUtils;
import com.example.springassignmentforum.web.handler.ResponseListDataUtils;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;

@Component
@Data
public class TestSubmitHelper<T, E> {

    public <T, E> ResponseEntity<ResponseDataUtils<T>> submitSingleDataResponse(String endpoint, E body, Class<T> classType, HttpMethod method, Boolean auth)
    {
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        var request = (body != null)? HttpHelper.getHttpEntity(body, auth) : HttpHelper.getHttpEntity(auth);
        var typeReference = new GenericClassHelper().getParameterizedTypeRef(ResponseDataUtils.class, classType);
        ResponseEntity<ResponseDataUtils<T>> response =
                testRestTemplate.exchange(endpoint, method, request, typeReference);

        return response;
    }
    public <T, E>  ResponseEntity<ResponseListDataUtils<T>> submitListDataResponse(String endpoint,  E body,  Class<T> classType, HttpMethod method, Boolean auth)
    {
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        var request = (body != null)? HttpHelper.getHttpEntity(body, auth) : HttpHelper.getHttpEntity(auth);
        var typeReference = new GenericClassHelper().getParameterizedTypeRef(ResponseListDataUtils.class, classType);
        ResponseEntity<ResponseListDataUtils<T>> response =
                testRestTemplate.exchange(endpoint, method, request, typeReference);

        return response;
    }
    public ResponseEntity<String> getFile(String endpoint, Boolean auth)
    {
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        var request = HttpHelper.getFileHttpEntity(auth);
        ResponseEntity<String> response =
                testRestTemplate.exchange(endpoint, HttpMethod.GET, request, String.class);

        return response;
    }
    public ResponseEntity<String> submitFile(String endpoint, LinkedMultiValueMap<String, Object> body,  HttpMethod method)
    {
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<LinkedMultiValueMap<String, Object>> request = new HttpEntity<LinkedMultiValueMap<String, Object>>(body, headers);

        ResponseEntity<String> response =
                testRestTemplate.exchange(endpoint, method, request, String.class);
        return response;
    }
}
