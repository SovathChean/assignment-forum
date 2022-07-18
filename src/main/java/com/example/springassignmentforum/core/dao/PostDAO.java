package com.example.springassignmentforum.core.dao;

import com.example.springassignmentforum.core.model.PostModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PostDAO extends JpaRepository<PostModel, Long> {
    @Query(
            value = "SELECT * FROM Posts WHERE user_id = :userId",
            nativeQuery = true)
    List<PostModel> findAllPostByUser(Long userId);
}
