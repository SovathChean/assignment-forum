package com.example.springassignmentforum.core.service;

import com.example.springassignmentforum.core.dto.PostCreationDTO;
import com.example.springassignmentforum.core.dto.PostDTO;
import com.example.springassignmentforum.web.filter.PostFilterCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {
    PostDTO createPost(PostCreationDTO postCreationDTO);
//    List<PostDTO> createPosts(List<PostCreationDTO> postCreationDTOS);
    List<PostDTO> getAllPost();
    PostDTO getPostById(long id);
    List<PostDTO> getAllPostByCreatorId(Long id);
    Object getAllPostPaginated(PostFilterCriteria postFilterCriteria);
 }
