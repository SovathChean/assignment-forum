package com.example.springassignmentforum.core.dao;

import com.example.springassignmentforum.core.model.FileModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileDAO extends JpaRepository<FileModel, Long> {
}
