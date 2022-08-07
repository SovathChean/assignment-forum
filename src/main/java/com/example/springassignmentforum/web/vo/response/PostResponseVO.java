package com.example.springassignmentforum.web.vo.response;

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
}
