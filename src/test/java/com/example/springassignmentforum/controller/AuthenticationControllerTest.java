package com.example.springassignmentforum.controller;

import com.example.springassignmentforum.core.constant.BasicTestUri;
import com.example.springassignmentforum.helper.TestSubmitHelper;
import com.example.springassignmentforum.web.handler.ResponseDataUtils;
import com.example.springassignmentforum.web.vo.request.LoginCreationRequestVO;
import com.example.springassignmentforum.web.vo.response.OAuthTokenResponseVO;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthenticationControllerTest {
    private static final String BasicURI = BasicTestUri.BASIC_TEST_URI.label;
    private static final String LoginURI = BasicURI + BasicTestUri.LOGIN.label;
    private static final String RefreshTokenURI = BasicURI + BasicTestUri.REFRESH_TOKEN.label;
    private static final String username = "sovath";
    private static final String password = "password";

    private final int port = 8080;

    @Test
    public void should_refresh_token()
    {
        String url = String.format(RefreshTokenURI, port);
        ResponseEntity<ResponseDataUtils<OAuthTokenResponseVO>> response = new TestSubmitHelper<>()
                .submitSingleDataResponse(url, null, OAuthTokenResponseVO.class, HttpMethod.GET, true);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getData());
        assertNotNull(response.getBody().getData().getRefreshToken());
        assertNotNull(response.getBody().getData().getAccessToken());
    }
    @Test
    public void should_logout()
    {
        String url = String.format(RefreshTokenURI, port);
        ResponseEntity<ResponseDataUtils<Object>> response = new TestSubmitHelper<>()
                .submitSingleDataResponse(url, null, Object.class, HttpMethod.GET, true);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
