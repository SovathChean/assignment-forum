package com.example.springassignmentforum.web.controller;

import com.example.springassignmentforum.core.common.filter.FilterRequest;
import com.example.springassignmentforum.core.common.utils.FilterUtils;
import com.example.springassignmentforum.core.dao.PostDAO;
import com.example.springassignmentforum.core.dto.PostCreationDTO;
import com.example.springassignmentforum.core.dto.PostDTO;
import com.example.springassignmentforum.core.service.PostService;
import com.example.springassignmentforum.web.filter.PostFilterCriteria;
import com.example.springassignmentforum.web.handler.ResponseHandler;
import com.example.springassignmentforum.web.vo.mapper.PostVOMapper;
import com.example.springassignmentforum.web.vo.request.PostCreationRequestVO;
import com.example.springassignmentforum.web.vo.response.PostResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api/posts")
public class PostController {
    @Autowired
    private PostService postService;
    public PostController(PostService postService)
    {
        this.postService = postService;
    }
    @PostMapping
    public ResponseEntity<Object> createPost(@RequestBody PostCreationRequestVO postCreationRequestVO)
    {

        PostCreationDTO postCreationDTO = PostVOMapper.INSTANCE.from(postCreationRequestVO);
        PostDTO postDTO = postService.createPost(postCreationDTO);
        PostResponseVO postResponseVO = PostVOMapper.INSTANCE.to(postDTO);

        return ResponseHandler.responseWithObject("Create Post Successfully", HttpStatus.CREATED, postResponseVO);
    }
    @GetMapping
    public ResponseEntity<Object> getPost()
    {
        List<PostDTO> postDTOList = postService.getAllPost();
        var res = PostVOMapper.INSTANCE.toList(postDTOList);

        return ResponseHandler.responseWithObject(null, HttpStatus.OK, res);
    }
    @GetMapping("/paginated")
    public ResponseEntity<Object> getPostPaginated(PostFilterCriteria postFilterCriteria)
    {

//        PostFilterCriteria postFilterCriteria = FilterUtils.createFilterCriteria(filterRequest, PostFilterCriteria.class);

        Object res = postService.getAllPostPaginated(postFilterCriteria);

        return ResponseHandler.responseWithObject(null, HttpStatus.OK, res);
    }
//    @GetMapping("/seed-post/{photoId}")
//    public ResponseEntity<Object> seedPost(@PathVariable(value = "photoId")Long photoId)
//    {
//
//        Object res = postService.getAllPostPaginated(postFilterCriteria);
//
//        return ResponseHandler.responseWithObject(null, HttpStatus.OK, "res");
//    }
    @GetMapping(value="/{id}")
    public ResponseEntity<Object> getPostById(@PathVariable(value="id") Long id)
    {
        PostDTO postDTO = postService.getPostById(id);

        return ResponseHandler.responseWithObject(null, HttpStatus.OK, postDTO);
    }
    @GetMapping(value="/creator/{userId}")
    public ResponseEntity<Object> getPostByCreatorId(@PathVariable(value="userId") Long userId)
    {
        List<PostDTO> postDTO = postService.getAllPostByCreatorId(userId);

        return ResponseHandler.responseWithObject(null, HttpStatus.OK, postDTO);
    }
}
