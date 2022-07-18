package com.example.springassignmentforum.core.dao;

import com.example.springassignmentforum.core.model.CommentModel;
import com.example.springassignmentforum.core.model.PostModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentDAO extends JpaRepository<CommentModel, Long> {

    @Query(
            value = "SELECT * FROM Comment WHERE user_id = :userId",
            nativeQuery = true)
    List<CommentModel> findAllCommentByUserId(Long userId);

    @Query(
            value = "SELECT * FROM Comments WHERE post_id = :postId",
            nativeQuery = true)
    List<CommentModel> findAllCommentByPostId(Long postId);

    @Query(
            value = "SELECT * FROM Comments WHERE parent_id = :parentId",
            nativeQuery = true)
    List<CommentModel> findAllCommentByParentId(Long parentId);
}
