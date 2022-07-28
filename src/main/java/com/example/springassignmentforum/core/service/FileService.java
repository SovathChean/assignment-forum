package com.example.springassignmentforum.core.service;

import com.example.springassignmentforum.core.dto.PostFileImageDTO;
import com.example.springassignmentforum.core.model.FileModel;
import org.springframework.core.env.Environment;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    void createFileDirectory(String folder);
    String getFileExtension(String fileName);
    String storeFile(String folder, MultipartFile file);
    List<FileModel> uploadListFile(List<MultipartFile> file);

    FileModel getFileModel(Long id);
}
