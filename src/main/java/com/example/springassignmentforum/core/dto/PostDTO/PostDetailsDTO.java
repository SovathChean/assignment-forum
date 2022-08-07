package com.example.springassignmentforum.core.dto.PostDTO;

import com.example.springassignmentforum.web.vo.response.PostCommentResponseVO;
import com.example.springassignmentforum.web.vo.response.PostFileResponseVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDetailsDTO {
    private Long id;
    private String subject;
    private String description;
    private Integer likes;
    private String creator;
    private Boolean isLike;
    private List<PostFileResponseVO> images;
    private List<PostCommentResponseVO> comments;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
