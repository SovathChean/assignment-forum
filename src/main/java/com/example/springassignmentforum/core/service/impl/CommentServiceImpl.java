package com.example.springassignmentforum.core.service.impl;

import com.example.springassignmentforum.core.dao.CommentDAO;
import com.example.springassignmentforum.core.dto.commentDTO.CommentCreationDTO;
import com.example.springassignmentforum.core.dto.commentDTO.CommentDTO;
import com.example.springassignmentforum.core.dto.PostDTO.PostCommentDTO;
import com.example.springassignmentforum.core.mapper.CommentMapper;
import com.example.springassignmentforum.core.model.CommentModel;
import com.example.springassignmentforum.core.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class CommentServiceImpl implements CommentService {

    @Autowired(required = false)
    private CommentDAO commentDAO;
    @Override
    public CommentDTO createComment(CommentCreationDTO commentCreationDTO) {
        CommentDTO commentDTO = CommentMapper.INSTANCE.from(commentCreationDTO);
        CommentModel commentModel = CommentMapper.INSTANCE.toProperty(commentDTO);
        commentModel.setCreatedAt(LocalDateTime.now());
        commentDAO.save(commentModel);
        log.info("Save comment");

        return CommentMapper.INSTANCE.fromProperty(commentModel);
    }
    @Override
    public List<PostCommentDTO> getCommentByPostID(Long postId) {
        log.info("Get comment by postIds");
        return commentDAO.findCommentsByPostId(postId);
    }
}
