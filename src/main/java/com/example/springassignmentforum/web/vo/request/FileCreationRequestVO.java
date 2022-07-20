package com.example.springassignmentforum.web.vo.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class FileCreationRequestVO {
    private List<MultipartFile> files;
}
