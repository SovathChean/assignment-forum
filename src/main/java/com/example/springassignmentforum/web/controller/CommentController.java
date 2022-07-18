package com.example.springassignmentforum.web.controller;

import com.example.springassignmentforum.core.dto.CommentCreationDTO;
import com.example.springassignmentforum.core.dto.CommentDTO;
import com.example.springassignmentforum.core.mapper.CommentMapper;
import com.example.springassignmentforum.core.service.CommentService;
import com.example.springassignmentforum.web.vo.mapper.CommentVOMapper;
import com.example.springassignmentforum.web.vo.request.CommentCreationRequestVO;
import com.example.springassignmentforum.web.vo.response.CommentResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<CommentResponseVO> createComment(@RequestBody CommentCreationRequestVO commentCreationRequestVO)
    {
        CommentCreationDTO commentCreationDTO = CommentVOMapper.INSTANCE.to(commentCreationRequestVO);
        CommentDTO comment = commentService.createComment(commentCreationDTO);
        CommentResponseVO res = CommentVOMapper.INSTANCE.from(comment);

        return ResponseEntity.ok(res);
    }
    @GetMapping("/post/{postId}")
    public ResponseEntity<List<CommentResponseVO>> getCommentByPostId(@PathVariable("postId") Long postId)
    {
        List<CommentDTO> commentDTOList = commentService.getAllCommentsByPostId(postId);

        return ResponseEntity.ok(CommentVOMapper.INSTANCE.toList(commentDTOList));
    }
    @GetMapping("/parent/{parentId}")
    public ResponseEntity<List<CommentResponseVO>> getReplies(@PathVariable("parentId") Long parentId)
    {
        List<CommentDTO> commentDTOList = commentService.getAllRepliesByParentId(parentId);

        return ResponseEntity.ok(CommentVOMapper.INSTANCE.toList(commentDTOList));
    }
}
