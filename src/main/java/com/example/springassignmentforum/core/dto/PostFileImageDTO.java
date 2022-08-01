package com.example.springassignmentforum.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostFileImageDTO {
    private Long fileId;
    private String fileLink;
}
