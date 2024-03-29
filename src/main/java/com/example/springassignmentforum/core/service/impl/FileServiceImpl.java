package com.example.springassignmentforum.core.service.impl;

import com.example.springassignmentforum.core.dao.FileDAO;
import com.example.springassignmentforum.core.model.FileModel;
import com.example.springassignmentforum.core.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class FileServiceImpl implements FileService {
    private static final String fileRootDirectory = "./uploads/files/";
    private Path fileStorageLocation;
    @Autowired
    private FileDAO fileDAO;
    @Override
    public void createFileDirectory(String folder) {
        this.fileStorageLocation = Paths.get(fileRootDirectory + folder + "/")
                                        .toAbsolutePath().normalize();
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
    public String storeFile(String folder, MultipartFile file)
    {
        String fileName = file.getOriginalFilename().isBlank() ? file.getName() : file.getOriginalFilename();
        createFileDirectory(folder);
        try
            {
                Path targetLocation = this.fileStorageLocation.resolve(fileName);
                Files.copy(file.getInputStream(), targetLocation);

                return fileName;
            }
        catch (IOException ex)
            {
                throw new RuntimeException("Could not store file " + fileName + ". Please try again!", ex);
            }
    }

    @Override
    public List<FileModel> uploadListFile(List<MultipartFile> files) {
        List<FileModel> fileModels = new ArrayList<>();
        LocalDateTime createdAt = LocalDateTime.now();
        for(MultipartFile file: files)
        {
            FileModel fileModel = new FileModel();
            UUID folderPath = UUID.randomUUID();
            String fileName = storeFile(folderPath.toString(), file);
            fileModel.setFileName(fileName);
            fileModel.setFolderPath(folderPath.toString());
            fileModel.setCreatedAt(createdAt);
            fileModel.setIsUsed(true);
            fileModels.add(fileModel);
        }
        log.info("Save all file");
        List<FileModel> saveFiles =  fileDAO.saveAll(fileModels);

        return saveFiles;
    }

    @Override
    public FileModel getFileModel(Long id) {
        log.info("Get file");
        FileModel file = fileDAO.findById(id).orElseThrow(() -> new RuntimeException("Data not found."));

        return file;
    }



}
