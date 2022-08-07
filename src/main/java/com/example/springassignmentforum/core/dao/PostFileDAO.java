package com.example.springassignmentforum.core.dao;

import com.example.springassignmentforum.core.dto.PostDTO.PostFileImageDTO;
import com.example.springassignmentforum.core.model.PostFileModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostFileDAO extends JpaRepository<PostFileModel, Long> {
    @Query(nativeQuery = true)
    List<PostFileImageDTO> findFileDetailByPostId(Long postId, String path);
    List<PostFileModel> findByFileId(Long fileId);
}
