package com.example.springassignmentforum.web.vo.response;

import com.example.springassignmentforum.core.dto.PostDTO;
import com.example.springassignmentforum.core.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponseVO {
    private Long id;
    private String subject;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UserModel user;

}
