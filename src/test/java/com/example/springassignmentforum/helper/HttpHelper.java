package com.example.springassignmentforum.helper;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

public class HttpHelper {
    private final static String bearerAuth="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjaGVhbiIsInRva2VuS2V5IjoiOTFmOGY4ZDYtYzI2Yi00NjMzLTgwMTItZTYxMzFhMGNhOTAxIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL2FwaS9yZWdpc3RlciIsImV4cCI6MTY1OTYwMzM5NH0.PUEA9drV99a_Qaiu-Owy8ulPfMBPkNkcKPZy5IgHdEY";

    public static HttpEntity<String> getHttpEntity() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(bearerAuth);
        httpHeaders.set("Content-Type", "application/json;charset=UTF-8");
        return new HttpEntity<>(httpHeaders);
    }

    public static <T> HttpEntity<T> getHttpEntity(T dto) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(bearerAuth);
        httpHeaders.set("Content-Type", "application/json;charset=UTF-8");
        return new HttpEntity<>(dto, httpHeaders);
    }
}
