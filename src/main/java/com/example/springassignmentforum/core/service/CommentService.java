package com.example.springassignmentforum.core.service;

import com.example.springassignmentforum.core.dto.CommentCreationDTO;
import com.example.springassignmentforum.core.dto.CommentDTO;
import com.example.springassignmentforum.core.dto.PostCommentDTO;


import java.util.List;

public interface CommentService{
    CommentDTO createComment(CommentCreationDTO commentCreationDTO);
    List<PostCommentDTO> getCommentByPostID(Long postId);
}
