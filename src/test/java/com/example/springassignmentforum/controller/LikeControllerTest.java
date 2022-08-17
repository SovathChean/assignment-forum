package com.example.springassignmentforum.controller;

import com.example.springassignmentforum.core.constant.BasicTestUri;
import com.example.springassignmentforum.core.dto.likeDTO.LikeCreationDTO;
import com.example.springassignmentforum.helper.TestSubmitHelper;
import com.example.springassignmentforum.web.handler.ResponseDataUtils;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LikeControllerTest {
    private static final String BasicURI = BasicTestUri.BASIC_TEST_URI.label;
    private static final String LikeUri = BasicURI + BasicTestUri.LIKE_URI.label;
    private static final Boolean isLike = true;
    private static final Long postId = (long) 12;
    private static final  Long creatorId = (long) 1;

    @Test
    public void should_create_like()
    {
        int port = 8080;
        String url = String.format(LikeUri, port);
        LikeCreationDTO likeCreationDTO = createLikeRequest();
        ResponseEntity<ResponseDataUtils<Object>> response = new TestSubmitHelper<>()
                .submitSingleDataResponse(url, likeCreationDTO, Object.class, HttpMethod.POST, true);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(Objects.requireNonNull(response.getBody()));
    }
    public LikeCreationDTO createLikeRequest()
    {
        LikeCreationDTO likeCreationDTO = new LikeCreationDTO();
        likeCreationDTO.setIsLike(isLike);
        likeCreationDTO.setUserId(creatorId);
        likeCreationDTO.setPostId(postId);

        return likeCreationDTO;
    }
}
