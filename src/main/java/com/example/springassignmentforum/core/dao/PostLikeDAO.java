package com.example.springassignmentforum.core.dao;

import com.example.springassignmentforum.core.model.PostLikeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostLikeDAO extends JpaRepository<PostLikeModel, Long> {
    @Query(value="Select likes From POST_LIKES Where post_id = :postId",
            nativeQuery = true
    )
    Integer findLikesByPostId(Long postId);
    @Query(value="Select * From POST_LIKES Where post_id = :postId",
            nativeQuery = true
    )
    PostLikeModel findPostLikeByPostId(Long postId);
}
