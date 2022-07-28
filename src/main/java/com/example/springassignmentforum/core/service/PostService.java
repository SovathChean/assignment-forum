package com.example.springassignmentforum.core.service;

import com.example.springassignmentforum.core.common.filter.PageFilterResult;
import com.example.springassignmentforum.core.dto.*;
import com.example.springassignmentforum.web.filter.PostFilterCriteria;
import com.example.springassignmentforum.web.vo.response.PostDetailResponseVO;

import java.util.List;

public interface PostService {
    PostDTO createPost(PostCreationDTO postCreationDTO);
//    List<PostDTO> createPosts(List<PostCreationDTO> postCreationDTOS);
    PageFilterResult<PostPaginatedVO> getAllPost(PostFilterCriteria postFilterCriteria);
    PostDTO getPostById(long id);
    List<PostDTO> getAllPostByCreatorId(Long id);
    PostDetailDTO getPostDetail(Long postId);
    List<PostFileImageDTO> getPostFileImageByPostId(Long postId);

 }
