package com.example.springassignmentforum.core.dao;

import com.example.springassignmentforum.core.model.PostLikeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostLikeDAO extends JpaRepository<PostLikeModel, Long> {
    PostLikeModel findByPostId(Long postId);
}
