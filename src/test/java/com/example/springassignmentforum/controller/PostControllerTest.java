package com.example.springassignmentforum.controller;

import com.example.springassignmentforum.core.constant.BasicTestUri;
import com.example.springassignmentforum.core.dto.PostUploadImageDTO;
import com.example.springassignmentforum.helper.TestSubmitHelper;
import com.example.springassignmentforum.web.handler.ResponseDataUtils;
import com.example.springassignmentforum.web.handler.ResponseListDataUtils;
import com.example.springassignmentforum.web.vo.request.PostCreationRequestVO;
import com.example.springassignmentforum.web.vo.request.PostUploadImageRequestVO;
import com.example.springassignmentforum.web.vo.response.PostDetailResponseVO;
import com.example.springassignmentforum.web.vo.response.PostResponseVO;
import com.example.springassignmentforum.web.vo.response.PostUploadImageResponseVO;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class PostControllerTest {
    private static final String BasicURI = BasicTestUri.BASIC_TEST_URI.label;
    private static final String PostURI = BasicURI + BasicTestUri.POST_URI.label;
    private static final String Subject = "PostTesting";
    private static final String Description = "For Testing Purpose";
    private static final String UpdateSubject = "UpdatePost";
    private static final String UpdateDescription = "For Update Purpose";
    private static final Long Creator = (long) 1;
    private static final Long PhotoId = (long) 23;
    private static final  String PostIdUrl = "/12";
    private static final  String UploadImageUrl = "/upload-image/12";
    private static final  String DeleteImageUrl = "/delete-image/12";
    private final int port = 8080;

    @Test
    public void should_create_post()
    {
        String url = String.format(PostURI, port);
        PostCreationRequestVO postCreationRequestVO = createPostRequest();
        ResponseEntity<ResponseDataUtils<PostResponseVO>> response = new TestSubmitHelper<>()
                .submitSingleDataResponse(url, postCreationRequestVO, PostResponseVO.class, HttpMethod.POST, true);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody().getData());
        assertEquals(Subject, response.getBody().getData().getSubject());
        assertEquals(Description, response.getBody().getData().getDescription());
    }
    @Test
    public void should_find_pagintaion_post()
    {
        String url = String.format(PostURI, port);
        ResponseEntity<ResponseListDataUtils<PostResponseVO>> response = new TestSubmitHelper<>()
                .submitListDataResponse(url, null, PostResponseVO.class, HttpMethod.GET, true);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getData());
    }
    @Test
    public void should_find_post_id()
    {
        String url = String.format(PostURI+"/12", port);
        ResponseEntity<ResponseDataUtils<PostDetailResponseVO>> response = new TestSubmitHelper<>()
                .submitSingleDataResponse(url, null, PostDetailResponseVO.class, HttpMethod.GET, true);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getData());
        assertNotNull(response.getBody().getData().getDescription());
        assertNotNull(response.getBody().getData().getSubject());
        assertNotNull(response.getBody().getData().getCreator());
        assertNotNull(response.getBody().getData().getImages());
        assertNotNull(response.getBody().getData().getLikes());
    }
    @Test
    public void should_find_post_owner()
    {
        String url = String.format(PostURI+"/owner", port);
        ResponseEntity<ResponseListDataUtils<PostResponseVO>> response = new TestSubmitHelper<>()
                .submitListDataResponse(url, null, PostResponseVO.class, HttpMethod.GET, true);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getData());
    }
    @Test
    public void should_update_post()
    {
        String url = String.format(PostURI+PostIdUrl, port);
        PostCreationRequestVO postCreationRequestVO = updatePostRequest();
        ResponseEntity<ResponseDataUtils<PostResponseVO>> response = new TestSubmitHelper<>()
                .submitSingleDataResponse(url, postCreationRequestVO, PostResponseVO.class, HttpMethod.POST, true);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody().getData());
        assertEquals(UpdateSubject, response.getBody().getData().getSubject());
        assertEquals(UpdateDescription, response.getBody().getData().getDescription());
    }
    @Test
    public void should_upload_image()
    {
        String url = String.format(PostURI+UploadImageUrl, port);
        PostUploadImageRequestVO postUploadImageRequestVO = uploadImageRequest();
        ResponseEntity<ResponseListDataUtils<PostUploadImageResponseVO>> response = new TestSubmitHelper<>()
                .submitListDataResponse(url, postUploadImageRequestVO, PostUploadImageResponseVO.class, HttpMethod.POST, true);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody().getData());
    }
    @Test
    public void should_delete_image()
    {
        String url = String.format(PostURI+DeleteImageUrl, port);
        PostUploadImageRequestVO postUploadImageRequestVO = uploadImageRequest();
        ResponseEntity<ResponseListDataUtils<PostUploadImageResponseVO>> response = new TestSubmitHelper<>()
                .submitListDataResponse(url, postUploadImageRequestVO, PostUploadImageResponseVO.class, HttpMethod.POST, true);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody().getData());
    }

    public PostCreationRequestVO createPostRequest()
    {
        PostCreationRequestVO postCreationRequestVO = new PostCreationRequestVO();
        postCreationRequestVO.setDescription(Description);
        postCreationRequestVO.setSubject(Subject);
        postCreationRequestVO.setUserId(Creator);

        return postCreationRequestVO;
    }
    public PostCreationRequestVO updatePostRequest()
    {
        PostCreationRequestVO postCreationRequestVO = new PostCreationRequestVO();
        postCreationRequestVO.setDescription(UpdateDescription);
        postCreationRequestVO.setSubject(UpdateSubject);
        postCreationRequestVO.setUserId(Creator);

        return postCreationRequestVO;
    }
    public PostUploadImageRequestVO uploadImageRequest()
    {
        Long[] photoId = new Long[1];
        photoId[0] = PhotoId;
        PostUploadImageRequestVO postUploadImageDTO = new PostUploadImageRequestVO();
        postUploadImageDTO.setPhotos(photoId);

        return postUploadImageDTO;
    }

}
