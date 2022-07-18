package com.example.springassignmentforum.core.service.impl;

import com.example.springassignmentforum.core.dao.CommentDAO;
import com.example.springassignmentforum.core.dto.CommentCreationDTO;
import com.example.springassignmentforum.core.dto.CommentDTO;
import com.example.springassignmentforum.core.mapper.CommentMapper;
import com.example.springassignmentforum.core.model.CommentModel;
import com.example.springassignmentforum.core.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDAO commentDAO;
    @Override
    public CommentDTO createComment(CommentCreationDTO commentCreationDTO) {
        CommentDTO commentDTO = CommentMapper.INSTANCE.from(commentCreationDTO);
        CommentModel commentModel = CommentMapper.INSTANCE.toProperty(commentDTO);
        commentDAO.save(commentModel);

        return commentDTO;
    }

    @Override
    public List<CommentDTO> getAllCommentsByCreatorId(Long userId) {
        List<CommentModel> commentModelList = commentDAO.findAllCommentByUserId(userId);

        return CommentMapper.INSTANCE.toListProperty(commentModelList);
    }

    @Override
    public List<CommentDTO> getAllCommentsByPostId(Long postId) {
        List<CommentModel> commentModelList = commentDAO.findAllCommentByPostId(postId);

        return CommentMapper.INSTANCE.toListProperty(commentModelList);
    }

    @Override
    public List<CommentDTO> getAllRepliesByParentId(Long parentId) {
        List<CommentModel> commentModelList = commentDAO.findAllCommentByParentId(parentId);

        return CommentMapper.INSTANCE.toListProperty(commentModelList);
    }
}
