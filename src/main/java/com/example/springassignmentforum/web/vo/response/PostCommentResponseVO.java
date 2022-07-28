package com.example.springassignmentforum.web.vo.response;

import com.example.springassignmentforum.core.model.PostModel;
import com.example.springassignmentforum.core.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostCommentResponseVO
{
    private Long id;
    private String message;
    private Long parentId;
    private String creator;
    private LocalDateTime createdAt;
}
