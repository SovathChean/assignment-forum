package com.example.springassignmentforum.core.repository;

import com.example.springassignmentforum.core.common.filter.PageFilterResult;
import com.example.springassignmentforum.core.model.PostModel;
import com.example.springassignmentforum.web.filter.PostFilterCriteria;
import org.springframework.data.domain.Pageable;



public interface PostRepository {
    PageFilterResult<PostModel> filterResult(PostFilterCriteria postFilterCriteria, Pageable pageable);
}
