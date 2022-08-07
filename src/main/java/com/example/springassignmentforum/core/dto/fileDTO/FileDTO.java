package com.example.springassignmentforum.core.dto.fileDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileDTO {
    private Long id;
    private String fileName;
    private Boolean isUsed;
    private LocalDateTime createdAt;
}
