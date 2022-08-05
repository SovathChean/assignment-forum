package com.example.springassignmentforum.helper;

import com.example.springassignmentforum.web.handler.ResponseDataUtils;
import com.example.springassignmentforum.web.handler.ResponseListDataUtils;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

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

}
