package com.example.springassignmentforum.web.controller;

import com.example.springassignmentforum.core.common.filter.PageFilterResult;
import com.example.springassignmentforum.core.dto.*;
import com.example.springassignmentforum.core.service.CommentService;
import com.example.springassignmentforum.core.service.PostService;
import com.example.springassignmentforum.core.service.UserService;
import com.example.springassignmentforum.web.filter.PostFilterCriteria;
import com.example.springassignmentforum.web.handler.ResponseDataUtils;
import com.example.springassignmentforum.web.handler.ResponseHandler;
import com.example.springassignmentforum.web.handler.ResponseListDataUtils;
import com.example.springassignmentforum.web.handler.ResponsePageUtils;
import com.example.springassignmentforum.web.vo.mapper.PostVOMapper;
import com.example.springassignmentforum.web.vo.request.PostCreationRequestVO;
import com.example.springassignmentforum.web.vo.response.PostDetailResponseVO;
import com.example.springassignmentforum.web.vo.response.PostResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping(value="/api/posts")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;

    public PostController(PostService postService, CommentService commentService)
    {
        this.postService = postService;
        this.commentService = commentService;
    }
    @PostMapping
    public ResponseEntity<ResponseDataUtils<PostResponseVO>> createPost(@RequestBody PostCreationRequestVO postCreationRequestVO)
    {
        UserDTO userDTO = userService.getAuthByName();
        postCreationRequestVO.setUserId(userDTO.getId());
        PostCreationDTO postCreationDTO = PostVOMapper.INSTANCE.from(postCreationRequestVO);
        PostDTO postDTO = postService.createPost(userDTO.getId(), postCreationDTO);
        PostResponseVO postResponseVO = PostVOMapper.INSTANCE.to(postDTO);

        return ResponseHandler.responseData("Create Post Successfully", HttpStatus.CREATED, postResponseVO);
    }
    @GetMapping()
    public ResponseEntity<ResponsePageUtils<PostResponseVO>> getAllPost(
            @RequestParam(value="fromDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) String fromDateTime,
            @RequestParam(value ="toDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) String toDateTime,
            PostFilterCriteria postFilterCriteria)
    {
        postFilterCriteria.setFromDateTime(this.convertStringToLocalDateTime(fromDateTime));
        postFilterCriteria.setToDateTime(this.convertStringToLocalDateTime(toDateTime));
        PageFilterResult<PostPaginatedDTO> page = postService.getAllPost(postFilterCriteria);
        PageFilterResult<PostResponseVO> res = new PageFilterResult<>(page.getTotalRows(), PostVOMapper.INSTANCE.fromPostPaginatedToPostResponseVO(page.getPageData()));

        return ResponseHandler.responsePagination(null, HttpStatus.OK, res);
    }
    @GetMapping(value="/{id}")
    public ResponseEntity<ResponseDataUtils<PostDetailResponseVO>> getPostById(@PathVariable(value="id") Long id)
    {
        PostDetailsDTO postDetailsDTO = postService.getPostDetail(id);
        PostDetailResponseVO postDetailResponseVO = PostVOMapper.INSTANCE.toPostDetail(postDetailsDTO);

        return ResponseHandler.responseData(null, HttpStatus.OK, postDetailResponseVO);
    }
    @GetMapping(value="/creator")
    public ResponseEntity<ResponsePageUtils<PostResponseVO>> getPostByCreatorId()
    {
        UserDTO userDTO = userService.getAuthByName();
        PageFilterResult<PostPaginatedDTO> page = postService.getAllPostByCreatorId(userDTO.getId());
        PageFilterResult<PostResponseVO> res = new PageFilterResult<>(page.getTotalRows(), PostVOMapper.INSTANCE.fromPostPaginatedToPostResponseVO(page.getPageData()));

        return ResponseHandler.responsePagination(null, HttpStatus.OK, res);
    }
    public LocalDateTime convertStringToLocalDateTime(String dateTime)
    {
        if(dateTime == null)
            return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime date = LocalDateTime.parse(dateTime, formatter);

        return date;
    }
}
