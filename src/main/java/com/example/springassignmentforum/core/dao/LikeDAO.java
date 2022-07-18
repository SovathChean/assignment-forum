package com.example.springassignmentforum.core.dao;

import com.example.springassignmentforum.core.model.LikeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.*;
import java.util.List;

@Repository
public interface LikeDAO extends JpaRepository<LikeModel, Long>{

}
