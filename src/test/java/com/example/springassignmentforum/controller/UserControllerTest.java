package com.example.springassignmentforum.controller;

import com.example.springassignmentforum.SpringAssignmentForumApplication;
import com.example.springassignmentforum.core.constant.BasicTestUri;
import com.example.springassignmentforum.core.service.UserService;
import com.example.springassignmentforum.helper.HttpHelper;
import com.example.springassignmentforum.web.handler.ResponseDataUtils;
import com.example.springassignmentforum.web.handler.ResponseListDataUtils;
import com.example.springassignmentforum.web.vo.request.UserCreationRequestVO;
import com.example.springassignmentforum.web.vo.response.OAuthTokenResponseVO;
import com.example.springassignmentforum.web.vo.response.UserResponseVO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.ResolvableType;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringAssignmentForumApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {
    private static final String BasicURI = BasicTestUri.BASIC_TEST_URI.label;
    private static final String RegisterURI = BasicURI + BasicTestUri.REGISTER_URI.label;
    private static final String UserURI = BasicURI + BasicTestUri.USER_URI;
    private static final String Name = "NameTest";
    private static final String Phone = "092123123";
    private static final String Password = "123123123";
    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private UserService userService;
    private HttpEntity<String> httpHeaders;
    @LocalServerPort
    private int port;

    public UserControllerTest()

    {
        this.httpHeaders = HttpHelper.getHttpEntity();
    }

    @Test
    public void should_register()
    {
        String url = String.format(RegisterURI, port);
        UserCreationRequestVO userCreationRequestVO = createRegisterRequest();
        HttpEntity<UserCreationRequestVO> request = HttpHelper.getHttpEntity(userCreationRequestVO);
        //Resolve Generic Class Type
        ResolvableType resolvableType = ResolvableType.forClassWithGenerics(ResponseDataUtils.class, OAuthTokenResponseVO.class);
        ParameterizedTypeReference<ResponseDataUtils<OAuthTokenResponseVO>> typeRef = ParameterizedTypeReference.forType(resolvableType.getType());
        ResponseEntity<ResponseDataUtils<OAuthTokenResponseVO>> response =
                testRestTemplate.exchange(url, HttpMethod.POST, request, typeRef);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getData().getAccessToken());
        assertNotNull(response.getBody().getData().getRefreshToken());
    }
    @Test
    public void should_get_all_users()
    {
        String url = String.format(UserURI, port);
        ResolvableType resolvableType = ResolvableType.forClassWithGenerics(ResponseListDataUtils.class, UserResponseVO.class);
        ParameterizedTypeReference<ResponseListDataUtils<UserResponseVO>> typeRef = ParameterizedTypeReference.forType(resolvableType.getType());
        ResponseEntity<ResponseListDataUtils<UserResponseVO>> response =
                testRestTemplate.exchange(url, HttpMethod.GET, this.httpHeaders, typeRef);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getData());
    }
    @Test
    public void should_get_user_by_id()
    {
        String url = String.format(UserURI+"/1", port);
        ResolvableType resolvableType = ResolvableType.forClassWithGenerics(ResponseListDataUtils.class, UserResponseVO.class);
        ParameterizedTypeReference<ResponseDataUtils<UserResponseVO>> typeRef = ParameterizedTypeReference.forType(resolvableType.getType());
        ResponseEntity<ResponseDataUtils<UserResponseVO>> response =
                testRestTemplate.exchange(url, HttpMethod.GET, this.httpHeaders, typeRef);
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
