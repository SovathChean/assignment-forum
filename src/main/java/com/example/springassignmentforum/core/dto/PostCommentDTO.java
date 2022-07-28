package com.example.springassignmentforum.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostCommentDTO {
    private Long id;
    private String message;
    private Long parentId;
    private String creator;
    private LocalDateTime createdAt;
}
