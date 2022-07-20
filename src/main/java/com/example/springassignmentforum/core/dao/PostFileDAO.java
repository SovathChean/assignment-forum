package com.example.springassignmentforum.core.dao;

import com.example.springassignmentforum.core.model.PostFileModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostFileDAO extends JpaRepository<PostFileModel, Long> {
}
