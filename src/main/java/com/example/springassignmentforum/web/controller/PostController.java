package com.example.springassignmentforum.web.controller;

import com.example.springassignmentforum.core.common.filter.PageFilterResult;
import com.example.springassignmentforum.core.dto.*;
import com.example.springassignmentforum.core.service.CommentService;
import com.example.springassignmentforum.core.service.PostService;
import com.example.springassignmentforum.web.filter.PostFilterCriteria;
import com.example.springassignmentforum.web.handler.ResponseHandler;
import com.example.springassignmentforum.web.vo.mapper.CommentVOMapper;
import com.example.springassignmentforum.web.vo.mapper.FileVOMapper;
import com.example.springassignmentforum.web.vo.mapper.PostVOMapper;
import com.example.springassignmentforum.web.vo.request.PostCreationRequestVO;
import com.example.springassignmentforum.web.vo.response.PostDetailResponseVO;
import com.example.springassignmentforum.web.vo.response.PostFileResponseVO;
import com.example.springassignmentforum.web.vo.response.PostResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping(value="/api/posts")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;

    public PostController(PostService postService, CommentService commentService)
    {

        this.postService = postService;
        this.commentService = commentService;
    }
    @PostMapping
    public ResponseEntity<Object> createPost(@RequestBody PostCreationRequestVO postCreationRequestVO)
    {

        PostCreationDTO postCreationDTO = PostVOMapper.INSTANCE.from(postCreationRequestVO);
        PostDTO postDTO = postService.createPost(postCreationDTO);
        PostResponseVO postResponseVO = PostVOMapper.INSTANCE.to(postDTO);

        return ResponseHandler.responseWithObject("Create Post Successfully", HttpStatus.CREATED, postResponseVO);
    }
    @GetMapping()
    public ResponseEntity<Object> getAllPost(
            @RequestParam(value="fromDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) String fromDateTime,
            @RequestParam(value ="toDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) String toDateTime,
            PostFilterCriteria postFilterCriteria)
    {

        postFilterCriteria.setFromDateTime(this.convertStringToLocalDateTime(fromDateTime));
        postFilterCriteria.setToDateTime(this.convertStringToLocalDateTime(toDateTime));
        System.out.println(postFilterCriteria);
        PageFilterResult<PostPaginatedVO> page = postService.getAllPost(postFilterCriteria);
        PageFilterResult<PostResponseVO> res = new PageFilterResult<>(page.getTotalRows(), PostVOMapper.INSTANCE.fromPostPaginatedToPostResponseVO(page.getPageData()));

        return ResponseHandler.responseWithObject(null, HttpStatus.OK, res);
    }
    @GetMapping(value="/{id}")
    public ResponseEntity<Object> getPostById(@PathVariable(value="id") Long id)
    {
        PostDetailResponseVO postDetailResponseVO = new PostDetailResponseVO();
        PostDetailDTO postDetail = postService.getPostDetail(id);
        List<PostCommentDTO> postCommentDTO = commentService.getCommentByPostID(id);
        List<PostFileImageDTO> postFileImageDTOS = postService.getPostFileImageByPostId(id);
        postDetailResponseVO = PostVOMapper.INSTANCE.toPostDetail(postDetail);
        postDetailResponseVO.setImages(FileVOMapper.INSTANCE.toListPostFile(postFileImageDTOS));
        postDetailResponseVO.setComments(CommentVOMapper.INSTANCE.toListPostCommentResponse(postCommentDTO));

        return ResponseHandler.responseWithObject(null, HttpStatus.OK, postDetailResponseVO);
    }
    @GetMapping(value="/creator/{userId}")
    public ResponseEntity<Object> getPostByCreatorId(@PathVariable(value="userId") Long userId)
    {
        List<PostDTO> postDTO = postService.getAllPostByCreatorId(userId);

        return ResponseHandler.responseWithObject(null, HttpStatus.OK, postDTO);
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
