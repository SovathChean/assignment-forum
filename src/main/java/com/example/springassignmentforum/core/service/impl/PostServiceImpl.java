package com.example.springassignmentforum.core.service.impl;

import com.example.springassignmentforum.core.dao.PostDAO;
import com.example.springassignmentforum.core.dto.PostCreationDTO;
import com.example.springassignmentforum.core.dto.PostDTO;
import com.example.springassignmentforum.core.mapper.PostMapper;
import com.example.springassignmentforum.core.model.PostModel;
import com.example.springassignmentforum.core.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired(required = false)
    private PostDAO postDAO;
    @Override
    public PostDTO createPost(PostCreationDTO postCreationDTO) {
        PostDTO postDTO = PostMapper.INSTANCE.from(postCreationDTO);
        PostModel postModel = PostMapper.INSTANCE.toProperty(postDTO);
        postModel.setCreatedAt(LocalDateTime.now());
        postDAO.save(postModel);
        return postDTO;
    }

    @Override
    public List<PostDTO> getAllPost() {
        var postDAOAll = postDAO.findAll();

        return postDAOAll.stream().map(post -> PostMapper.INSTANCE.fromProperty(post)).collect(Collectors.toList());
    }
    @Override
    public PostDTO getPostById(long id)
    {
        PostModel postModel = postDAO.findById(id).orElseThrow(() -> new RuntimeException("Post Not Found."));

        return PostMapper.INSTANCE.fromProperty(postModel);
    }
    @Override
    public List<PostDTO> getAllPostByCreatorId(Long userId) {
        List<PostModel> postModels = postDAO.findAllPostByUser(userId);

        return PostMapper.INSTANCE.fromListProperty(postModels);
    }
}
