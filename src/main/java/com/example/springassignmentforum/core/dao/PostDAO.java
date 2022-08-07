package com.example.springassignmentforum.core.dao;

import com.example.springassignmentforum.core.dto.PostDTO.PostCountFilterDto;
import com.example.springassignmentforum.core.dto.PostDTO.PostDetailDTO;
import com.example.springassignmentforum.core.dto.PostDTO.PostPaginatedDTO;
import com.example.springassignmentforum.core.model.PostModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PostDAO extends JpaRepository<PostModel, Long> {
    List<PostModel> findAllPostByUser(Long userId);
    @Query(nativeQuery = true)
    PostDetailDTO findPostDetails(Long postId);
    @Query(nativeQuery = true)
    List<PostPaginatedDTO> findAllPostFilters(String search, LocalDateTime fromDate, LocalDateTime toDate, Long creatorId, Integer pageSize, Integer offset);
    @Query(nativeQuery = true)
    PostCountFilterDto findTotalRowPostFilters(String search, LocalDateTime fromDate, LocalDateTime toDate, Long creatorId);
}
