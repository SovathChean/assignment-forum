package com.example.springassignmentforum.core.dao;

import com.example.springassignmentforum.core.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<UserModel, Long> {
    UserModel findUserById(Long userId);
    UserModel findUserByName(String name);
}
