package com.example.springassignmentforum.core.dao;

import com.example.springassignmentforum.core.dto.PostDTO.CountPostDTO;
import com.example.springassignmentforum.core.model.LikeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeDAO extends JpaRepository<LikeModel, Long>{
    CountPostDTO findLikeByUserIdAndPostId(Long userId, Long postId);
}
