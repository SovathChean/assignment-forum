package com.example.springassignmentforum.core.dao;

import com.example.springassignmentforum.core.model.PostModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface PostDAO extends JpaRepository<PostModel, Long> {
    @Query(
            value = "SELECT * FROM Posts WHERE user_id = :userId",
            nativeQuery = true)
    List<PostModel> findAllPostByUser(Long userId);
    Page<PostModel> findAllBySubjectStartingWith(String subject, Pageable pageable);
    Page<PostModel> findAllByDescriptionStartingWith(String description, Pageable pageable);
    Page<PostModel> findAllByCreatedAtBetween(LocalDateTime from, LocalDateTime to, Pageable pageable);
}
