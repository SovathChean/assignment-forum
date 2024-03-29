package com.example.springassignmentforum.core.dto.likeDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeCreationDTO {
    private Long postId;
    private Long userId;
    private Boolean isLike;
}
