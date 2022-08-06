package com.example.springassignmentforum.controller;

import com.example.springassignmentforum.core.constant.BasicTestUri;
import com.example.springassignmentforum.core.dto.CommentCreationDTO;
import com.example.springassignmentforum.core.dto.UserDTO;
import com.example.springassignmentforum.core.service.AuthenticationService;
import com.example.springassignmentforum.core.service.impl.UserServiceImpl;
import com.example.springassignmentforum.helper.TestSubmitHelper;
import com.example.springassignmentforum.web.handler.ResponseDataUtils;
import com.example.springassignmentforum.web.vo.response.CommentResponseVO;
import com.example.springassignmentforum.web.vo.response.UserResponseVO;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommentControllerTest {
    private static final String BasicURI = BasicTestUri.BASIC_TEST_URI.label;
    private static final String CommentURI = BasicURI + BasicTestUri.COMMENT.label;
    private static final String message = "Comment!";
    private static final Long postId = (long) 12;
    private static final  Long creatorId = (long) 1;
    private final int port = 8080;
    @Test
    public void should_make_comment()
    {
        String url = String.format(CommentURI, port);
        CommentCreationDTO commentCreationDTO = createCommentRequest();
        ResponseEntity<ResponseDataUtils<CommentResponseVO>> response = new TestSubmitHelper<>()
                .submitSingleDataResponse(url, commentCreationDTO, CommentResponseVO.class, HttpMethod.POST, true);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getData());
        assertEquals(message, response.getBody().getData().getMessage());
        assertNotNull(response.getBody().getData().getId());
    }
    public CommentCreationDTO createCommentRequest()
    {
        CommentCreationDTO commentCreationDTO = new CommentCreationDTO();
        commentCreationDTO.setMessage(message);
        commentCreationDTO.setUserId(creatorId);
        commentCreationDTO.setPostId(postId);

        return commentCreationDTO;
    }
}
