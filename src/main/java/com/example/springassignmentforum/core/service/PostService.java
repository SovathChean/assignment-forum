package com.example.springassignmentforum.core.service;

import com.example.springassignmentforum.core.common.filter.PageFilterResult;
import com.example.springassignmentforum.core.dto.PostCreationDTO;
import com.example.springassignmentforum.core.dto.PostDTO;
import com.example.springassignmentforum.core.dto.PostDetailDTO;
import com.example.springassignmentforum.core.dto.PostFileImageDTO;
import com.example.springassignmentforum.web.filter.PostFilterCriteria;
import com.example.springassignmentforum.web.vo.response.PostDetailResponseVO;

import java.util.List;

public interface PostService {
    PostDTO createPost(PostCreationDTO postCreationDTO);
//    List<PostDTO> createPosts(List<PostCreationDTO> postCreationDTOS);
    PageFilterResult<PostDTO> getAllPost(PostFilterCriteria postFilterCriteria);
    PostDTO getPostById(long id);
    List<PostDTO> getAllPostByCreatorId(Long id);
    PostDetailDTO getPostDetail(Long postId);
    List<PostFileImageDTO> getPostFileImageByPostId(Long postId);

 }
