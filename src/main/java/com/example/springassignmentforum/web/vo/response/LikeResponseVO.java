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
public class LikeResponseVO {
    private Long id;
    private UserModel users;
    private PostModel posts;
    private Boolean isLike;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
