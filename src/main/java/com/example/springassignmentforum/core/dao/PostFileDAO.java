package com.example.springassignmentforum.core.dao;

import com.example.springassignmentforum.core.model.PostFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostFileDAO extends JpaRepository<PostFile, Long> {
}
