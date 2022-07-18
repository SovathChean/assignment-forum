package com.example.springassignmentforum.core.dao;

import com.example.springassignmentforum.core.model.PostLikeModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeDAO extends JpaRepository<PostLikeModel, Long> {
}
