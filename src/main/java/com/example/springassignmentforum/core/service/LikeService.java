package com.example.springassignmentforum.core.service;

import com.example.springassignmentforum.core.dto.likeDTO.LikeCreationDTO;
import com.example.springassignmentforum.core.dto.likeDTO.LikeDTO;


public interface LikeService {
    LikeDTO createLike(LikeCreationDTO likeCreationDTO);
    Boolean isLike(Long userId, Long postId);
}
