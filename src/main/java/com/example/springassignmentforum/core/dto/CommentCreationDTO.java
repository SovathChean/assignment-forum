package com.example.springassignmentforum.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentCreationDTO {
    private String message;
    private Long postId;
    private Long parentId;
}
