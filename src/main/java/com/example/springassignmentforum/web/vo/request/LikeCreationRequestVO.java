package com.example.springassignmentforum.web.vo.request;

import lombok.Data;

@Data
public class LikeCreationRequestVO {
    private Long userId;
    private Long postId;
    private Boolean isLike;
}
