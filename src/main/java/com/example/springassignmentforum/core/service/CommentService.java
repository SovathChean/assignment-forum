package com.example.springassignmentforum.core.service;

import com.example.springassignmentforum.core.dto.commentDTO.CommentCreationDTO;
import com.example.springassignmentforum.core.dto.commentDTO.CommentDTO;
import com.example.springassignmentforum.core.dto.PostDTO.PostCommentDTO;


import java.util.List;

public interface CommentService{
    CommentDTO createComment(CommentCreationDTO commentCreationDTO);
    List<PostCommentDTO> getCommentByPostID(Long postId);
}
