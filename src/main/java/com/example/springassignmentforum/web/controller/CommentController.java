package com.example.springassignmentforum.web.controller;

import com.example.springassignmentforum.core.dto.commentDTO.CommentCreationDTO;
import com.example.springassignmentforum.core.dto.commentDTO.CommentDTO;
import com.example.springassignmentforum.core.service.CommentService;
import com.example.springassignmentforum.web.handler.ResponseDataUtils;
import com.example.springassignmentforum.web.handler.ResponseHandler;
import com.example.springassignmentforum.web.vo.mapper.CommentVOMapper;
import com.example.springassignmentforum.web.vo.request.CommentCreationRequestVO;
import com.example.springassignmentforum.web.vo.response.CommentResponseVO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api/comments")
@SecurityRequirement(name = "bearerAuth")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @PostMapping
    public ResponseEntity<ResponseDataUtils<CommentResponseVO>> createComment(@RequestBody CommentCreationRequestVO commentCreationRequestVO)
    {
        Long userId = 1L; //getIdFromAuth
        commentCreationRequestVO.setUserId(userId);
        CommentCreationDTO commentCreationDTO = CommentVOMapper.INSTANCE.to(commentCreationRequestVO);
        CommentDTO comment = commentService.createComment(commentCreationDTO);
        CommentResponseVO res = CommentVOMapper.INSTANCE.from(comment);

        return ResponseHandler.responseData("Comment have been sent.", HttpStatus.CREATED, res);
    }
}
