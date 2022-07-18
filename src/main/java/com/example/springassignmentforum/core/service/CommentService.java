package com.example.springassignmentforum.core.service;

import com.example.springassignmentforum.core.dto.CommentCreationDTO;
import com.example.springassignmentforum.core.dto.CommentDTO;


import java.util.List;

public interface CommentService{
    CommentDTO createComment(CommentCreationDTO commentCreationDTO);
    List<CommentDTO> getAllCommentsByCreatorId(Long userId);
    List<CommentDTO> getAllCommentsByPostId(Long postId);
}
