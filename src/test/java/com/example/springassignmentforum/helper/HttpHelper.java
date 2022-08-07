package com.example.springassignmentforum.helper;

import com.example.springassignmentforum.utility.AccessTokenUtility;
import org.springframework.http.*;

import java.util.Collections;


public class HttpHelper {
    public static HttpEntity<String> getHttpEntity(Boolean auth) {
        HttpHeaders httpHeaders = new HttpHeaders();
        if(auth) httpHeaders.setBearerAuth(getAccessToken());
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return new HttpEntity<>(httpHeaders);
    }
    public static HttpEntity<String> getFileHttpEntity(Boolean auth) {
        HttpHeaders httpHeaders = new HttpHeaders();
        if(auth) httpHeaders.setBearerAuth(getAccessToken());
        return new HttpEntity<>(httpHeaders);
    }

    public static <T> HttpEntity<T> getHttpEntity(T dto, Boolean auth) {
        HttpHeaders httpHeaders = new HttpHeaders();
        if(auth) httpHeaders.setBearerAuth(getAccessToken());
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return new HttpEntity<>(dto, httpHeaders);
    }
    public static String getAccessToken()
    {
        AccessTokenUtility accessTokenUtility = new AccessTokenUtility();
        return accessTokenUtility.getAccessToken();
    }

}
