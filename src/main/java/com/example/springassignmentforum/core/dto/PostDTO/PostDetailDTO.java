package com.example.springassignmentforum.core.dto.PostDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDetailDTO {
    private Long id;
    private String subject;
    private String description;
    private Integer likes;
    private String creator;
}
