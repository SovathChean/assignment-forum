package com.example.springassignmentforum.web.controller;

import com.example.springassignmentforum.core.dao.PostDAO;
import com.example.springassignmentforum.core.dto.PostCreationDTO;
import com.example.springassignmentforum.core.dto.PostDTO;
import com.example.springassignmentforum.core.service.PostService;
import com.example.springassignmentforum.web.vo.mapper.PostVOMapper;
import com.example.springassignmentforum.web.vo.request.PostCreationRequestVO;
import com.example.springassignmentforum.web.vo.response.PostResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<PostResponseVO> createPost(@RequestBody PostCreationRequestVO postCreationRequestVO)
    {

        PostCreationDTO postCreationDTO = PostVOMapper.INSTANCE.from(postCreationRequestVO);
        PostDTO postDTO = postService.createPost(postCreationDTO);
        PostResponseVO postResponseVO = PostVOMapper.INSTANCE.to(postDTO);

        return ResponseEntity.ok(postResponseVO);
    }
    @GetMapping
    public ResponseEntity<List<PostResponseVO>> getPost()
    {
        List<PostDTO> postDTOList = postService.getAllPost();
        var res = PostVOMapper.INSTANCE.toList(postDTOList);

        return ResponseEntity.ok(res);
    }
    @GetMapping(value="/{id}")
    public ResponseEntity<PostResponseVO> getPostById(@PathVariable(value="id") Long id)
    {
        PostDTO postDTO = postService.getPostById(id);

        return ResponseEntity.ok(PostVOMapper.INSTANCE.to(postDTO));
    }
    @GetMapping(value="/creator/{userId}")
    public ResponseEntity<List<PostResponseVO>> getPostByCreatorId(@PathVariable(value="userId") Long userId)
    {
        List<PostDTO> postDTO = postService.getAllPostByCreatorId(userId);

        return ResponseEntity.ok(PostVOMapper.INSTANCE.toList(postDTO));
    }
}
