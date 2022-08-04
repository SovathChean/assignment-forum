package com.example.springassignmentforum.web.controller;

import com.example.springassignmentforum.core.model.FileModel;
import com.example.springassignmentforum.core.service.FileService;
import com.example.springassignmentforum.web.handler.ResponseHandler;
import com.example.springassignmentforum.web.handler.ResponseListDataUtils;
import com.example.springassignmentforum.web.vo.request.FileCreationRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
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
    public ResponseEntity<ResponseListDataUtils<FileModel>> uploadFile(FileCreationRequestVO fileCreationRequestVO)
    {
        List<FileModel> fileModels =  fileService.uploadListFile(fileCreationRequestVO.getFiles());

        return ResponseHandler.responseListData("File have been upload.", HttpStatus.OK, fileModels);
    }
    @GetMapping(value="{id}")
    public ResponseEntity<Object> getFileById(@PathVariable("id") Long id) throws FileNotFoundException {
        FileModel fileModel = fileService.getFileModel(id);
        String path = "./uploads/files/" + fileModel.getFolderPath() + "/" + fileModel.getFileName();

        return ResponseHandler.responseDownload(path, fileModel.getFileName());
    }
}
