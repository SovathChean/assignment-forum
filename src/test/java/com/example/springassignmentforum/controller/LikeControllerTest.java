package com.example.springassignmentforum.controller;

import com.example.springassignmentforum.core.constant.BasicTestUri;
import com.example.springassignmentforum.core.dto.LikeCreationDTO;
import com.example.springassignmentforum.core.dto.UserDTO;
import com.example.springassignmentforum.core.service.impl.UserServiceImpl;
import com.example.springassignmentforum.helper.TestSubmitHelper;
import com.example.springassignmentforum.web.handler.ResponseDataUtils;
import com.example.springassignmentforum.web.vo.response.CommentResponseVO;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LikeControllerTest {
    private static final String BasicURI = BasicTestUri.BASIC_TEST_URI.label;
    private static final String LikeUri = BasicURI + BasicTestUri.LIKE_URI.label;
    private static final Boolean isLike = true;
    private static final Long postId = (long) 12;
    private final int port = 8080;
    public void should_create_like()
    {
        String url = String.format(LikeUri, port);
        LikeCreationDTO likeCreationDTO = createLikeRequest();
        ResponseEntity<ResponseDataUtils<Object>> response = new TestSubmitHelper<>()
                .submitSingleDataResponse(url, likeCreationDTO, Object.class, HttpMethod.POST, true);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
    public LikeCreationDTO createLikeRequest()
    {
        UserServiceImpl userService = new UserServiceImpl();
        UserDTO userDTO = userService.getAuthByName();
        LikeCreationDTO likeCreationDTO = new LikeCreationDTO();
        likeCreationDTO.setIsLike(isLike);
        likeCreationDTO.setUserId(userDTO.getId());
        likeCreationDTO.setPostId(postId);

        return likeCreationDTO;
    }
}
