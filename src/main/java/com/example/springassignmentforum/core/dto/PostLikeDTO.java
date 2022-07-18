package com.example.springassignmentforum.core.dto;

import com.example.springassignmentforum.core.model.PostModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostLikeDTO {
    private Long postId;
    private Integer likes;
}
