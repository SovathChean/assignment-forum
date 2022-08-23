package com.example.springassignmentforum.core.dao;

import com.example.springassignmentforum.core.model.OAuthTokenModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OAuthTokenDAO extends JpaRepository<OAuthTokenModel, Long> {
   OAuthTokenModel findByUniqueKey(String uniqueKey);
}
