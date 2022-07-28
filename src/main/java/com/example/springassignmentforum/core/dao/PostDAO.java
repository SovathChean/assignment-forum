package com.example.springassignmentforum.core.dao;

import com.example.springassignmentforum.core.dto.PostDTO;
import com.example.springassignmentforum.core.dto.PostDetailDTO;
import com.example.springassignmentforum.core.dto.PostPaginatedVO;
import com.example.springassignmentforum.core.model.PostModel;
import com.example.springassignmentforum.web.vo.response.PostDetailResponseVO;
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
//    Page<PostModel> findAllBySubjectContainingIgnoreCase(String subject, Pageable pageable);
//    Page<PostModel> findAllByDescriptionContainingIgnoreCase(String description, Pageable pageable);
//    Page<PostModel> findAllBySubjectContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrCreatedAtBetween(String subject, String description, LocalDateTime from, LocalDateTime to, Pageable pageable);
//    Page<PostModel> findAllByCreatedAtBetween(LocalDateTime from, LocalDateTime to, Pageable pageable);
//    Page<PostModel> findAllBySubjectContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String subject, String description, Pageable pageable);
    @Query(nativeQuery = true)
    PostDetailDTO findPostDetails(Long postId);
    @Query(nativeQuery = true)
    Page<PostPaginatedVO> findAllPostFilters(String search, LocalDateTime fromDate, LocalDateTime toDate, Pageable pageable);

}
