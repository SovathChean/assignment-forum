package com.example.springassignmentforum.core.service.impl;

import com.example.springassignmentforum.core.dao.FileDAO;
import com.example.springassignmentforum.core.model.FileModel;
import com.example.springassignmentforum.core.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    private Path fileStorageLocation;
    @Autowired
    private FileDAO fileDAO;

    @Override
    public void FileStorageService(Environment env) {
        this.fileStorageLocation = Paths.get(env.getProperty("app.file.upload-dir", "./uploads/files"))
                .toAbsolutePath().normalize();
        System.out.println(this.fileStorageLocation.toString());
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    @Override
    public String getFileExtension(String fileName) {
        if (fileName == null) {
            return null;
        }
        String[] fileNameParts = fileName.split("\\.");

        return fileNameParts[fileNameParts.length - 1];
    }

    @Override
    public String storeFile(MultipartFile file)
    {
        String fileName = new Date().getTime() + "-file." + getFileExtension(file.getOriginalFilename());
        try
            {
                    Path targetLocation = this.fileStorageLocation.resolve(fileName);
                    Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

                    return fileName;
            }
        catch (IOException ex)
            {
                throw new RuntimeException("Could not store file " + fileName + ". Please try again!", ex);
            }
    }

    @Override
    public List<FileModel> uploadFile(List<MultipartFile> files) {
        List<FileModel> fileModels = new ArrayList<>();
        LocalDateTime createdAt = LocalDateTime.now();
        for(MultipartFile file: files)
        {
            FileModel fileModel = new FileModel();
            String fileName = storeFile(file);
            fileModel.setFileName(fileName);
            fileModel.setCreatedAt(createdAt);
            fileModels.add(fileModel);
        }
        List<FileModel> saveFiles =  fileDAO.saveAll(fileModels);

        return saveFiles;
    }


}
