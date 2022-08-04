package com.example.springassignmentforum.core.dao;

import com.example.springassignmentforum.core.dto.CountPostDTO;
import com.example.springassignmentforum.core.model.LikeModel;
import com.example.springassignmentforum.core.model.PostModel;
import com.example.springassignmentforum.core.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.*;
import java.util.List;

@Repository
public interface LikeDAO extends JpaRepository<LikeModel, Long>{
    CountPostDTO findLikeByUserIdAndPostId(Long userId, Long postId);
}
