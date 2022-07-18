package com.example.springassignmentforum.web.vo.response;

import com.example.springassignmentforum.core.model.CommentModel;
import com.example.springassignmentforum.core.model.PostModel;
import com.example.springassignmentforum.core.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponseVO {
    private Long id;
    private String message;
    private UserModel users;
    private PostModel posts;
    private CommentModel parent;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
