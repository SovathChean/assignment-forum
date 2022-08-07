package com.example.springassignmentforum.core.dto.PostDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostUploadImageDTO {
    private Long[] photos;
}
