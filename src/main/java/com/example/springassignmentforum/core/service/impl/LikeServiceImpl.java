package com.example.springassignmentforum.core.service.impl;

import com.example.springassignmentforum.core.dao.LikeDAO;
import com.example.springassignmentforum.core.dao.PostDAO;
import com.example.springassignmentforum.core.dao.PostLikeDAO;
import com.example.springassignmentforum.core.dto.likeDTO.LikeCreationDTO;
import com.example.springassignmentforum.core.dto.likeDTO.LikeDTO;
import com.example.springassignmentforum.core.dto.PostDTO.PostLikeDTO;
import com.example.springassignmentforum.core.mapper.LikeMapper;
import com.example.springassignmentforum.core.mapper.PostLikeMapper;
import com.example.springassignmentforum.core.model.LikeModel;
import com.example.springassignmentforum.core.model.PostLikeModel;
import com.example.springassignmentforum.core.service.LikeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class LikeServiceImpl implements LikeService {
    @Autowired(required = false)
    private LikeDAO likeDAO;
    @Autowired(required = false)
    private PostDAO postDAO;
    @Autowired(required = false)
    private PostLikeDAO postLikeDAO;
    @Override
    public LikeDTO createLike(LikeCreationDTO likeCreationDTO) {
        Long postId = likeCreationDTO.getPostId();
        LikeDTO likeDTO = LikeMapper.INSTANCE.from(likeCreationDTO);
        likeDTO.setCreatedAt(LocalDateTime.now());
        PostLikeModel postLikeModel = postLikeDAO.findByPostId(postId);
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
        log.info("Create like");

        LikeModel likeModel = LikeMapper.INSTANCE.toProperty(likeDTO);
        likeModel.setPosts(postDAO.findById(postId).get());
        likeDAO.save(likeModel);
        postLikeDAO.save(postLikeModel);

        return null;
    }

    @Override
    public Boolean isLike(Long userId, Long postId) {

        return (likeDAO.findLikeByUserIdAndPostId(userId, postId).getLikeCount() != 0) ;
    }

}
