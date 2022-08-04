package com.example.springassignmentforum.controller;

import com.example.springassignmentforum.SpringAssignmentForumApplication;
import com.example.springassignmentforum.core.constant.BasicTestUri;
import com.example.springassignmentforum.core.service.UserService;
import com.example.springassignmentforum.helper.HttpHelper;
import com.example.springassignmentforum.web.handler.ResponseDataUtils;
import com.example.springassignmentforum.web.vo.request.PostCreationRequestVO;
import com.example.springassignmentforum.web.vo.request.UserCreationRequestVO;
import com.example.springassignmentforum.web.vo.response.OAuthTokenResponseVO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.ResolvableType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringAssignmentForumApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostControllerTest {
    private static final String BasicURI = BasicTestUri.BASIC_TEST_URI.label;
    private static final String PostURI = BasicURI + BasicTestUri.POST_URI.label;
    private static final String UserURI = BasicURI + BasicTestUri.USER_URI;
    private static final String Subject = "PostTesting";
    private static final String Description = "For Testing Purpose";
    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private UserService userService;
    @LocalServerPort
    private int port;

    public void should_create_post()
    {
        String url = String.format(PostURI, port);
        PostCreationRequestVO postCreationRequestVO = createPostRequest();
        ResponseDataUtils<OAuthTokenResponseVO> authTokenResponseVOResponseDataUtils = new ResponseDataUtils<>();
        HttpEntity<PostCreationRequestVO> request = HttpHelper.getHttpEntity(postCreationRequestVO);
        //Resolve Generic Class Type
        ResolvableType resolvableType = ResolvableType.forClassWithGenerics(ResponseDataUtils.class, OAuthTokenResponseVO.class);
        ParameterizedTypeReference<ResponseDataUtils<OAuthTokenResponseVO>> typeRef = ParameterizedTypeReference.forType(resolvableType.getType());
        ResponseEntity<ResponseDataUtils<OAuthTokenResponseVO>> response =
                testRestTemplate.exchange(url, HttpMethod.POST, request, typeRef);

        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        System.out.println(response.getBody());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getData().getAccessToken());
        assertNotNull(response.getBody().getData().getRefreshToken());
    }
    public PostCreationRequestVO createPostRequest()
    {
        PostCreationRequestVO postCreationRequestVO = new PostCreationRequestVO();

//        postCreationRequestVO.setUserId(Name);
//        postCreationRequestVO.setPhone(Phone);

        return postCreationRequestVO;
    }

}
