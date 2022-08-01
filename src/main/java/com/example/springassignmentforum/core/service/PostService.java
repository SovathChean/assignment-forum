package com.example.springassignmentforum.core.service;

import com.example.springassignmentforum.core.common.filter.PageFilterResult;
import com.example.springassignmentforum.core.dto.*;
import com.example.springassignmentforum.web.filter.PostFilterCriteria;

import java.util.List;

public interface PostService {
    PostDTO createPost(Long userId, PostCreationDTO postCreationDTO);
//    List<PostDTO> createPosts(List<PostCreationDTO> postCreationDTOS);
    PageFilterResult<PostPaginatedDTO> getAllPost(PostFilterCriteria postFilterCriteria);
    PostDTO getPostById(long id);
    PageFilterResult<PostPaginatedDTO> getAllPostByCreatorId(Long id);
    PostDetailsDTO getPostDetail(Long postId);
    List<PostFileImageDTO> getPostFileImageByPostId(Long postId);

 }
