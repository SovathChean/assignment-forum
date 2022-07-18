package com.example.springassignmentforum.core.service;

import com.example.springassignmentforum.core.dto.PostCreationDTO;
import com.example.springassignmentforum.core.dto.PostDTO;

import java.util.List;

public interface PostService {
    PostDTO createPost(PostCreationDTO postCreationDTO);
    List<PostDTO> getAllPost();
    PostDTO getPostById(long id);
    List<PostDTO> getAllPostByCreatorId(Long id);
}
