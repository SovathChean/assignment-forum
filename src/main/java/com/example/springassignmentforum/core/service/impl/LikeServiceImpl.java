package com.example.springassignmentforum.core.service.impl;

import com.example.springassignmentforum.core.dao.LikeDAO;
import com.example.springassignmentforum.core.dao.PostLikeDAO;
import com.example.springassignmentforum.core.dto.CountPostDTO;
import com.example.springassignmentforum.core.dto.LikeCreationDTO;
import com.example.springassignmentforum.core.dto.LikeDTO;
import com.example.springassignmentforum.core.dto.PostLikeDTO;
import com.example.springassignmentforum.core.mapper.LikeMapper;
import com.example.springassignmentforum.core.mapper.PostLikeMapper;
import com.example.springassignmentforum.core.model.LikeModel;
import com.example.springassignmentforum.core.model.PostLikeModel;
import com.example.springassignmentforum.core.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LikeServiceImpl implements LikeService {
    @Autowired(required = false)
    private LikeDAO likeDAO;
    @Autowired(required = false)
    private PostLikeDAO postLikeDAO;
    @Override
    public LikeDTO createLike(LikeCreationDTO likeCreationDTO) {
        Long postId = likeCreationDTO.getPostId();
        LikeDTO likeDTO = LikeMapper.INSTANCE.from(likeCreationDTO);
        likeDTO.setCreatedAt(LocalDateTime.now());
        PostLikeModel postLikeModel = postLikeDAO.findPostLikeByPostId(postId);
        //InitialLike
        if (postLikeModel == null) {
            PostLikeDTO postLikeDTO = new PostLikeDTO();
            postLikeDTO.setLikes(1);
            postLikeDTO.setPostId(postId);
            postLikeModel = PostLikeMapper.INSTANCE.toProperty(postLikeDTO);
        }
        else
        {
            Integer incrementLike = postLikeModel.getLikes() + 1;
            if(!likeCreationDTO.getIsLike())
            {
                incrementLike = postLikeModel.getLikes() - 1;
                likeDTO.setIsLike(false);
            }
            postLikeModel.setLikes(incrementLike);
        }

        LikeModel likeModel = LikeMapper.INSTANCE.toProperty(likeDTO);
        likeDAO.save(likeModel);
        postLikeDAO.save(postLikeModel);

        return null;
    }

    @Override
    public Boolean isLike(Long userId, Long postId) {

        return (likeDAO.findLikeByUserIdAndPostId(userId, postId).getLikeCount() != 0) ;
    }

}
