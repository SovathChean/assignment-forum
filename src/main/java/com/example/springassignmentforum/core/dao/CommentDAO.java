package com.example.springassignmentforum.core.dao;

import com.example.springassignmentforum.core.dto.PostCommentDTO;
import com.example.springassignmentforum.core.model.CommentModel;
import com.example.springassignmentforum.core.model.PostModel;
import com.example.springassignmentforum.web.vo.response.PostCommentResponseVO;
import com.example.springassignmentforum.web.vo.response.PostDetailResponseVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CommentDAO extends JpaRepository<CommentModel, Long> {
    @Query(nativeQuery = true)
    List<PostCommentDTO> findCommentsByPostId(Long postId);

}
