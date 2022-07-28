//package com.example.springassignmentforum.core.repository.impl;
//
//import com.example.springassignmentforum.core.common.filter.PageFilterResult;
//import com.example.springassignmentforum.core.dao.PostDAO;
//import com.example.springassignmentforum.core.dto.PostDTO;
//import com.example.springassignmentforum.core.mapper.PostMapper;
//import com.example.springassignmentforum.core.model.PostModel;
//import com.example.springassignmentforum.core.repository.PostRepository;
//import com.example.springassignmentforum.web.filter.PostFilterCriteria;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Repository;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.LocalTime;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.List;
//
//@Repository
//public class PostRepositoryImpl implements PostRepository {
//    @Autowired
//    private PostDAO postDAO;
//
//    @Override
//    public PageFilterResult<PostDTO> filterResult(PostFilterCriteria postFilterCriteria, Pageable pageable) {
//         String subject = StringUtils.isNotBlank(postFilterCriteria.getSubject())? postFilterCriteria.getSubject(): "";
//         String description =StringUtils.isNotBlank(postFilterCriteria.getDescription())? postFilterCriteria.getDescription(): "";
//         LocalDateTime fromDate = postFilterCriteria.getFromDateTime();
//         LocalDateTime toDate = postFilterCriteria.getToDateTime();
//         List<PostModel> data = new ArrayList<>();
//         Long totalRaw = (long) 0;
//
//         if(!subject.equals("") && !description.equals("") && fromDate != null && toDate != null )
//         {
//             var page =  postDAO.findAllBySubjectContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrCreatedAtBetween
//                     (subject, description, fromDate, toDate, pageable);
//             data = page.getContent();
//             totalRaw =page.getTotalElements();
//         }
//         else if(!subject.equals(""))
//         {
//             var page =  postDAO.findAllBySubjectContainingIgnoreCase(subject, pageable);
//
//             data = page.getContent();
//             totalRaw =page.getTotalElements();
//         }
//         else if(!description.equals(""))
//         {
//             var page =  postDAO.findAllByDescriptionContainingIgnoreCase(description, pageable);
//
//             data = page.getContent();
//             totalRaw =page.getTotalElements();
//         }
//         else if(!subject.equals("") && !description.equals(""))
//         {
//             var page =  postDAO.findAllBySubjectContainingIgnoreCaseOrDescriptionContainingIgnoreCase(subject, description, pageable);
//
//             data = page.getContent();
//             totalRaw =page.getTotalElements();
//         }
//         else if(fromDate != null && toDate != null)
//         {
//             var page =  postDAO.findAllByCreatedAtBetween(fromDate, toDate, pageable);
//             data = page.getContent();
//             totalRaw =page.getTotalElements();
//         }
//         else
//         {
//             var page =  postDAO.findAll( pageable);
//             data = page.getContent();
//             totalRaw =page.getTotalElements();
//         }
//
//        return new PageFilterResult<>(totalRaw, PostMapper.INSTANCE.fromListProperty(data));
//    }
//
//}
