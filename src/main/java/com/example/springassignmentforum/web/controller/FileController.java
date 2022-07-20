package com.example.springassignmentforum.web.controller;

import com.example.springassignmentforum.core.model.FileModel;
import com.example.springassignmentforum.core.service.FileService;
import com.example.springassignmentforum.web.response.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/api/file")
public class FileController {
    @Autowired
    private FileService fileService;

    public FileController(FileService fileService)
    {
        this.fileService = fileService;
    }
    @PostMapping
    public ResponseEntity<Object> uploadFile(@RequestBody List<MultipartFile> fileList)
    {
        List<FileModel> fileModels =  fileService.uploadFile(fileList);

        return ResponseHandler.responseWithObject(null, HttpStatus.ACCEPTED, fileModels);
    }
}
