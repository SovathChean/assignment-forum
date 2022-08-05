package com.example.springassignmentforum.utility;

import com.example.springassignmentforum.core.constant.BasicTestUri;
import com.example.springassignmentforum.helper.HttpHelper;
import com.example.springassignmentforum.helper.TestSubmitHelper;
import com.example.springassignmentforum.web.handler.ResponseDataUtils;
import com.example.springassignmentforum.web.vo.request.LoginCreationRequestVO;
import com.example.springassignmentforum.web.vo.response.OAuthTokenResponseVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.ResolvableType;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Data
@Component
public class AccessTokenUtility {
    private static final String BasicURI = BasicTestUri.BASIC_TEST_URI.label;
    private final String username = "sovath";
    private final  String password = "password";
    public String getAccessToken()
    {
        RestTemplate restTemplate = new RestTemplateBuilder().build();
        String url = String.format( BasicURI+ "login", 8080);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("username", "sovath");
        map.add("password", "password");
        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(map, headers);
        ResponseEntity<Object> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                Object.class
        );

        Map<String, Object> res = new ObjectMapper().convertValue(response.getBody(), Map.class);

        return (String) res.get("accessToken");
    }

}
