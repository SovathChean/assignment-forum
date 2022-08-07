package com.example.springassignmentforum.core.dto.commentDTO;

import com.example.springassignmentforum.core.model.CommentModel;
import com.example.springassignmentforum.core.model.PostModel;
import com.example.springassignmentforum.core.model.UserModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private Long id;
    private String message;
    private PostModel posts;
    private UserModel users;
    private Long parentId;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;
}
