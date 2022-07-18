package com.example.springassignmentforum.core.service.impl;

import com.example.springassignmentforum.core.dto.CommentCreationDTO;
import com.example.springassignmentforum.core.dto.CommentDTO;
import com.example.springassignmentforum.core.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Override
    public CommentDTO createComment(CommentCreationDTO commentCreationDTO) {
        return null;
    }

    @Override
    public List<CommentDTO> getAllCommentsByCreatorId(Long userId) {
        return null;
    }

    @Override
    public List<CommentDTO> getAllCommentsByPostId(Long postId) {
        return null;
    }

    @Override
    public List<CommentDTO> getAllRepliesByParentId(Long parentId) {
        return null;
    }
}
