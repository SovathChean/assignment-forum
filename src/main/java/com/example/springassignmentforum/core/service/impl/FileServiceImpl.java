package com.example.springassignmentforum.core.service.impl;

import com.example.springassignmentforum.core.dao.FileDAO;
import com.example.springassignmentforum.core.model.FileModel;
import com.example.springassignmentforum.core.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    private static final String fileRootDirectory = "./uploads/files/";
    private Path fileStorageLocation;
    @Autowired
    private FileDAO fileDAO;
    @Override
    public void createFileDirectory(String folder) {
        this.fileStorageLocation = Paths.get(this.fileRootDirectory + folder + "/")
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
        String fileName = file.getOriginalFilename().split("\\.")[0] + "." + getFileExtension(file.getOriginalFilename());
        createFileDirectory(folder);
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
        List<FileModel> saveFiles =  fileDAO.saveAll(fileModels);

        return saveFiles;
    }

    @Override
    public FileModel getFileModel(Long id) {

        FileModel file = fileDAO.findById(id).orElseThrow(() -> new RuntimeException("Data not found."));

        return file;
    }


}
