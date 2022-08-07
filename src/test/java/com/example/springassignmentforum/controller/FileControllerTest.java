package com.example.springassignmentforum.controller;

import com.example.springassignmentforum.core.constant.BasicTestUri;
import com.example.springassignmentforum.helper.TestSubmitHelper;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;


import java.io.File;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileControllerTest {
    private static final String BasicURI = BasicTestUri.BASIC_TEST_URI.label;
    private static final String FileUri = BasicURI + BasicTestUri.FILE_URI.label;
    private final int port = 8080;

    @Test
    public void should_upload_file() throws IOException {
        String url = String.format(FileUri, port);
        var file = new File("files/image.png");
        LinkedMultiValueMap<String, Object> parameters = new LinkedMultiValueMap<String, Object>();
        var uploadFile = new FileSystemResource(file);
        parameters.add("files", uploadFile);
        ResponseEntity<String> response = new TestSubmitHelper<>()
                .submitFile(url, parameters, HttpMethod.POST);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
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
