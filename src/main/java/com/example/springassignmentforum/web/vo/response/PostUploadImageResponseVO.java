package com.example.springassignmentforum.web.vo.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostUploadImageResponseVO {
    private Long id;
    private Long postId;
    private Long fileId;
}
