package com.example.springassignmentforum.core.service.impl;

import com.example.springassignmentforum.core.dao.CommentDAO;
import com.example.springassignmentforum.core.dto.CommentCreationDTO;
import com.example.springassignmentforum.core.dto.CommentDTO;
import com.example.springassignmentforum.core.dto.PostCommentDTO;
import com.example.springassignmentforum.core.mapper.CommentMapper;
import com.example.springassignmentforum.core.model.CommentModel;
import com.example.springassignmentforum.core.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired(required = false)
    private CommentDAO commentDAO;
    @Override
    public CommentDTO createComment(CommentCreationDTO commentCreationDTO) {
        CommentDTO commentDTO = CommentMapper.INSTANCE.from(commentCreationDTO);
        CommentModel commentModel = CommentMapper.INSTANCE.toProperty(commentDTO);
        commentModel.setCreatedAt(LocalDateTime.now());
        commentDAO.save(commentModel);

        return CommentMapper.INSTANCE.fromProperty(commentModel);
    }
    @Override
    public List<PostCommentDTO> getCommentByPostID(Long postId) {
        return commentDAO.findCommentsByPostId(postId);
    }
}
