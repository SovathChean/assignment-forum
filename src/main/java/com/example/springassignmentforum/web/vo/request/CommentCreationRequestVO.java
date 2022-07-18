package com.example.springassignmentforum.web.vo.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class CommentCreationRequestVO {
    @NotBlank
    private String message;
    @NotBlank
    @NotEmpty
    private Long postId;
    @NotBlank
    @NotEmpty
    private Long parentId;
}
