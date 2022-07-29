package com.example.springassignmentforum.web.vo.response;

import com.example.springassignmentforum.core.dto.PostCommentDTO;
import com.example.springassignmentforum.core.dto.PostFileImageDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private List<PostFileResponseVO> images;
    private List<PostCommentResponseVO> comments;
}
