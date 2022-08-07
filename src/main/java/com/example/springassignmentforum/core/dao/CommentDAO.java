package com.example.springassignmentforum.core.dao;

import com.example.springassignmentforum.core.dto.PostDTO.PostCommentDTO;
import com.example.springassignmentforum.core.model.CommentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CommentDAO extends JpaRepository<CommentModel, Long> {
    @Query(nativeQuery = true)
    List<PostCommentDTO> findCommentsByPostId(Long postId);

}
