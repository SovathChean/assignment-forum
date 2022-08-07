package com.example.springassignmentforum.controller;

import com.example.springassignmentforum.core.constant.BasicTestUri;
import com.example.springassignmentforum.core.model.FileModel;
import com.example.springassignmentforum.helper.TestSubmitHelper;
import com.example.springassignmentforum.web.handler.ResponseDataUtils;
import com.example.springassignmentforum.web.handler.ResponseListDataUtils;
import com.example.springassignmentforum.web.vo.request.FileCreationRequestVO;
import org.apache.tomcat.util.json.JSONParserConstants;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileControllerTest {
    private static final String BasicURI = BasicTestUri.BASIC_TEST_URI.label;
    private static final String FileUri = BasicURI + BasicTestUri.FILE_URI.label;
    private final int port = 8080;

    @Test
    public void should_upload_file() throws IOException {
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        String url = String.format(FileUri, port);
        List<MultipartFile> fileBody = new ArrayList<>();
        MultipartFile multipartFile = new MockMultipartFile(
                "file.png",
                new FileInputStream(Paths.get("files/image.png").toFile())
        );
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        MultiValueMap<String, Object> body
                = new LinkedMultiValueMap<>();
        body.add("file", multipartFile.getBytes());
        HttpEntity<MultiValueMap<String, Object>> requestEntity
                = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = testRestTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
    @Test
    public void should_get_file()
    {
        String url = String.format(FileUri+"/23", port);
        var response = new TestSubmitHelper<>()
                .getFile(url,  true);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
