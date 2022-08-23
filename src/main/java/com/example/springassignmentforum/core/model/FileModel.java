package com.example.springassignmentforum.core.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="Files")
public class FileModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String folderPath;
    private String fileName;
    private Boolean isUsed;
    private LocalDateTime createdAt;
}
