package com.example.springassignmentforum.web.controller;

import com.example.springassignmentforum.core.dto.CommentCreationDTO;
import com.example.springassignmentforum.core.dto.CommentDTO;
import com.example.springassignmentforum.core.service.CommentService;
import com.example.springassignmentforum.web.handler.ResponseHandler;
import com.example.springassignmentforum.web.vo.mapper.CommentVOMapper;
import com.example.springassignmentforum.web.vo.request.CommentCreationRequestVO;
import com.example.springassignmentforum.web.vo.response.CommentResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;
    public CommentController(CommentService commentService)
    {
        this.commentService = commentService;
    }
    @PostMapping
    public ResponseEntity<Object> createComment(@RequestBody CommentCreationRequestVO commentCreationRequestVO)
    {
        Long userId = new Long(1); //getIdFromAuth
        commentCreationRequestVO.setUserId(userId);
        CommentCreationDTO commentCreationDTO = CommentVOMapper.INSTANCE.to(commentCreationRequestVO);
        CommentDTO comment = commentService.createComment(commentCreationDTO);
        CommentResponseVO res = CommentVOMapper.INSTANCE.from(comment);

        return ResponseHandler.responseWithObject("Comment have been sent.", HttpStatus.CREATED, res);
    }
    @GetMapping("/post/{postId}")
    public ResponseEntity<Object> getCommentByPostId(@PathVariable("postId") Long postId)
    {
        List<CommentDTO> commentDTOList = commentService.getAllCommentsByPostId(postId);

        return ResponseHandler.responseWithObject(null, HttpStatus.OK, commentDTOList);
    }
    @GetMapping("/parent/{parentId}")
    public ResponseEntity<Object> getReplies(@PathVariable("parentId") Long parentId)
    {
        List<CommentDTO> commentDTOList = commentService.getAllRepliesByParentId(parentId);

        return ResponseHandler.responseWithObject(null, HttpStatus.OK, commentDTOList);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<Object> getCommentByUserId(@PathVariable("userId") Long userId)
    {
        List<CommentDTO> commentDTOList = commentService.getAllCommentsByCreatorId(userId);

        return ResponseHandler.responseWithObject(null, HttpStatus.OK, commentDTOList);
    }
}
