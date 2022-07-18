package com.example.springassignmentforum.core.service;

import com.example.springassignmentforum.core.dto.LikeCreationDTO;
import com.example.springassignmentforum.core.dto.LikeDTO;
import org.springframework.stereotype.Service;


public interface LikeService {
    LikeDTO createLike(LikeCreationDTO likeCreationDTO);
}
