package com.example.springassignmentforum.core.dao;

import com.example.springassignmentforum.core.dto.PostDetailDTO;
import com.example.springassignmentforum.core.dto.PostPaginatedDTO;
import com.example.springassignmentforum.core.model.PostModel;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PostDAO extends JpaRepository<PostModel, Long> {
    @Query(
            value = "SELECT * FROM Posts WHERE user_id = :userId",
            nativeQuery = true)
    List<PostModel> findAllPostByUser(Long userId);
    @Query(nativeQuery = true)
    PostDetailDTO findPostDetails(Long postId);
    @Query(nativeQuery = true)
    Page<PostPaginatedDTO> findAllPostFilters(String search, LocalDateTime fromDate, LocalDateTime toDate, Long creatorId, Pageable pageable);

}
