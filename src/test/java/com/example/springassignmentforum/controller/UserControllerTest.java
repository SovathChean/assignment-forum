package com.example.springassignmentforum.controller;

import com.example.springassignmentforum.core.constant.BasicTestUri;
import com.example.springassignmentforum.helper.TestSubmitHelper;
import com.example.springassignmentforum.web.handler.ResponseDataUtils;
import com.example.springassignmentforum.web.handler.ResponseListDataUtils;
import com.example.springassignmentforum.web.vo.request.UserCreationRequestVO;
import com.example.springassignmentforum.web.vo.response.OAuthTokenResponseVO;
import com.example.springassignmentforum.web.vo.response.UserResponseVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserControllerTest {
    private static final String BasicURI = BasicTestUri.BASIC_TEST_URI.label;
    private static final String RegisterURI = BasicURI + BasicTestUri.REGISTER_URI.label;
    private static final String UserURI = BasicURI + BasicTestUri.USER_URI.label;
    private static final String Name = "NameTest";
    private static final String Phone = "092123123";
    private static final String Password = "123123123";
    private final int port = 8080;

//    @Test
    public void should_register()
    {
        String url = String.format(RegisterURI, port);
        UserCreationRequestVO userCreationRequestVO = createRegisterRequest();
        ResponseEntity<ResponseDataUtils<OAuthTokenResponseVO>> response = new TestSubmitHelper<>()
                .submitSingleDataResponse(url, userCreationRequestVO, OAuthTokenResponseVO.class, HttpMethod.POST, false);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getData().getAccessToken());
        assertNotNull(response.getBody().getData().getRefreshToken());
    }
    @Test
    public void should_get_all_users()
    {
        String url = String.format(UserURI, port);
        ResponseEntity<ResponseListDataUtils<UserResponseVO>> response = new TestSubmitHelper<>()
                .submitListDataResponse(url, null, UserResponseVO.class, HttpMethod.GET, true);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getData());
    }
    @Test
    public void should_get_user_by_id()
    {
        String url = String.format(UserURI+"/1", port);
        ResponseEntity<ResponseDataUtils<UserResponseVO>> response = new TestSubmitHelper<>()
                .submitSingleDataResponse(url, null, UserResponseVO.class, HttpMethod.GET, true);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getData());
    }
    public UserCreationRequestVO createRegisterRequest()
    {
        UserCreationRequestVO userCreationRequestVO = new UserCreationRequestVO();
        userCreationRequestVO.setName(Name);
        userCreationRequestVO.setPhone(Phone);
        userCreationRequestVO.setPassword(Password);

        return userCreationRequestVO;
    }

}
