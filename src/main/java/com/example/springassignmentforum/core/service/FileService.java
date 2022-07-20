package com.example.springassignmentforum.core.service;

import com.example.springassignmentforum.core.model.FileModel;
import org.springframework.core.env.Environment;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    void FileStorageService(Environment env);
    String getFileExtension(String fileName);
    String storeFile(MultipartFile file);
    List<FileModel> uploadFile(List<MultipartFile> file);
}
