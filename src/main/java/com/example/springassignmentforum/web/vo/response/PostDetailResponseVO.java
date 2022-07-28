package com.example.springassignmentforum.web.vo.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDetailResponseVO {
    private Long id;
    private String subject;
    private String description;
    private Integer likes;
    private String creator;
    private List<PostCommentResponseVO> comments;
    private List<PostFileResponseVO> images;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
