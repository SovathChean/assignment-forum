package com.example.springassignmentforum.core.service.impl;

import com.example.springassignmentforum.core.dao.LikeDAO;
import com.example.springassignmentforum.core.dao.PostLikeDAO;
import com.example.springassignmentforum.core.dto.LikeCreationDTO;
import com.example.springassignmentforum.core.dto.LikeDTO;
import com.example.springassignmentforum.core.dto.PostLikeDTO;
import com.example.springassignmentforum.core.mapper.LikeMapper;
import com.example.springassignmentforum.core.mapper.PostLikeMapper;
import com.example.springassignmentforum.core.model.LikeModel;
import com.example.springassignmentforum.core.model.PostLikeModel;
import com.example.springassignmentforum.core.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;

public class LikeServiceImpl implements LikeService {
    @Autowired
    private LikeDAO likeDAO;
    @Autowired
    private PostLikeDAO postLikeDAO;
    @Override
    public LikeDTO createLike(LikeCreationDTO likeCreationDTO) {
        LikeDTO likeDTO = LikeMapper.INSTANCE.from(likeCreationDTO);
        PostLikeDTO postLikeDTO = new PostLikeDTO();
        Integer incrementlike = postLikeDTO.getLikes() + 1;
        Long postId = likeCreationDTO.getPostId();
        postLikeDTO.setLikes(incrementlike);
        postLikeDTO.setPostId(postId);
        LikeModel likeModel = LikeMapper.INSTANCE.toProperty(likeDTO);
        PostLikeModel postLikeModel = PostLikeMapper.INSTANCE.toProperty(postLikeDTO);

        likeDAO.save(likeModel);
        postLikeDAO.save(postLikeModel);

        return null;
    }
}
