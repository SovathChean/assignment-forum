package com.example.springassignmentforum.controller;

import com.example.springassignmentforum.SpringAssignmentForumApplication;
import com.example.springassignmentforum.core.constant.BasicTestUri;
import com.example.springassignmentforum.core.service.UserService;
import com.example.springassignmentforum.helper.HttpHelper;
import com.example.springassignmentforum.web.vo.request.UserCreationRequestVO;
import com.example.springassignmentforum.web.vo.response.UserResponseVO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringAssignmentForumApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {
    private static final String BasicURI = BasicTestUri.BASIC_TEST_URI.label;
    private static final String RegisterURI = BasicURI + BasicTestUri.REGISER_URI.label;
    private static final String UserURI = BasicURI + BasicTestUri.USER_URI;
    private static final String Name = "NameTest";
    private static final String Phone = "092123123";
    private static final String Password = "123123123";
    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private UserService userService;
    @LocalServerPort
    private int port;

    @Test
    public void should_register()
    {
        String url = String.format(RegisterURI, port);
        UserCreationRequestVO userCreationRequestVO = createRegisterRequest();
        HttpEntity<UserCreationRequestVO> request = HttpHelper.getHttpEntity(userCreationRequestVO);
        ResponseEntity<Object> response = testRestTemplate.exchange(url, HttpMethod.POST, request, Object.class);
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        assertNotNull(response.getBody());
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
