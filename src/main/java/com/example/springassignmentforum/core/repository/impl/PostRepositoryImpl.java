package com.example.springassignmentforum.core.repository.impl;

import com.example.springassignmentforum.core.common.filter.PageFilterResult;
import com.example.springassignmentforum.core.dao.PostDAO;
import com.example.springassignmentforum.core.model.PostModel;
import com.example.springassignmentforum.core.repository.PostRepository;
import com.example.springassignmentforum.web.filter.PostFilterCriteria;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PostRepositoryImpl implements PostRepository {
    @Autowired
    private PostDAO postDAO;
    @Override
    public PageFilterResult<PostModel> filterResult(PostFilterCriteria postFilterCriteria, Pageable pageable) {
         System.out.println(postFilterCriteria);
         String subject = StringUtils.isNotBlank(postFilterCriteria.getSubject())? postFilterCriteria.getSubject(): null;
         String description =StringUtils.isNotBlank(postFilterCriteria.getDescription())? postFilterCriteria.getDescription(): null;
//         LocalDateTime fromTime = postFilterCriteria.getFromTime();
//         LocalDateTime toTime = postFilterCriteria.getToTime();
//         LocalDate fromDate = postFilterCriteria.getFromDate();
//         LocalDate toDate = postFilterCriteria.getToDate();
         List<PostModel> data = new ArrayList<>();
         Long totalRaw = (long) 0;

         if(subject != null)
         {
             var page =  postDAO.findAllBySubjectStartingWith(subject, pageable);
             data = page.getContent();
             totalRaw =page.getTotalElements();
         }
         else if(description != null)
         {
             var page =  postDAO.findAllBySubjectStartingWith(subject, pageable);
             data = page.getContent();
             totalRaw =page.getTotalElements();
         }
         else
         {
             var page =  postDAO.findAll( pageable);
             data = page.getContent();
             totalRaw =page.getTotalElements();
         }

        return new PageFilterResult<>(totalRaw, data);
    }

}
