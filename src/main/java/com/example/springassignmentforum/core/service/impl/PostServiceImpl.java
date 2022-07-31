package com.example.springassignmentforum.core.service.impl;

import com.example.springassignmentforum.core.common.filter.PageFilterResult;
import com.example.springassignmentforum.core.dao.CommentDAO;
import com.example.springassignmentforum.core.dao.FileDAO;
import com.example.springassignmentforum.core.dao.PostDAO;
import com.example.springassignmentforum.core.dao.PostFileDAO;
import com.example.springassignmentforum.core.dto.*;
import com.example.springassignmentforum.core.mapper.CommentMapper;
import com.example.springassignmentforum.core.mapper.PostFileMapper;
import com.example.springassignmentforum.core.mapper.PostMapper;
import com.example.springassignmentforum.core.model.CommentModel;
import com.example.springassignmentforum.core.model.PostFileModel;
import com.example.springassignmentforum.core.model.PostModel;
import com.example.springassignmentforum.core.service.PostService;

import com.example.springassignmentforum.web.filter.PostFilterCriteria;
import com.example.springassignmentforum.web.vo.response.CommentResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class PostServiceImpl implements PostService {
    @Autowired(required = false)
    private PostDAO postDAO;
    @Autowired(required = false)
    private PostFileDAO postFileDAO;
    @Autowired(required = false)
    private CommentDAO commentDAO;
    @Autowired(required = false)
    private FileDAO fileDAO;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public PostDTO createPost(PostCreationDTO postCreationDTO) {
        PostDTO postDTO = PostMapper.INSTANCE.from(postCreationDTO);
        PostModel postModel = PostMapper.INSTANCE.toProperty(postDTO);
        postModel.setCreatedAt(LocalDateTime.now());
        try
        {
            postDAO.save(postModel);
            List<PostFileModel> postFileModels = this.getPostFileModel(postModel.getId(), postCreationDTO.getPhotos());
            postFileDAO.saveAll(postFileModels);
        }
        catch (Exception e)
        {
            throw e;
        }

        return postDTO;
    }

    @Override
    public PostDTO getPostById(long id)
    {
        PostModel postModel = postDAO.findById(id).orElseThrow(() -> new RuntimeException("Post Not Found."));

        return PostMapper.INSTANCE.fromProperty(postModel);
    }
    @Override
    public List<PostDTO> getAllPostByCreatorId(Long userId) {
        List<PostModel> postModels = postDAO.findAllPostByUser(userId);

        return PostMapper.INSTANCE.fromListProperty(postModels);
    }
    @Override
    public PostDetailDTO getPostDetail(Long postId) {

        return postDAO.findPostDetails(postId);
    }
    @Override
    public List<PostFileImageDTO> getPostFileImageByPostId(Long postId) {
        return postFileDAO.findFileDetailByPostId(postId);
    }

    @Override
    public PageFilterResult<PostPaginatedVO> getAllPost(PostFilterCriteria postFilterCriteria) {
        List<PostPaginatedVO> data = new ArrayList<>();
        System.out.println(postFilterCriteria);
        Long totalRow = (long) 0;
        if(!postFilterCriteria.getPaginated())
        {
            data = PostMapper.INSTANCE.fromPostEntityToPaginatedResponse(postDAO.findAll());
            totalRow = postDAO.count();
            return new PageFilterResult<PostPaginatedVO>(totalRow, data);
        }
        Pageable paging = PageRequest.of(postFilterCriteria.getPageNo() - 1, postFilterCriteria.getPageSize(), Sort.by(postFilterCriteria.getDEFAULT_ORDER_BY()));
        Page<PostPaginatedVO> postDTOList = postDAO.findAllPostFilters(postFilterCriteria.getSearch(), postFilterCriteria.getFromDateTime(), postFilterCriteria.getToDateTime(), paging);

        return new PageFilterResult<>(postDTOList.getTotalElements(), postDTOList.getContent());
    }

    public List<PostFileModel> getPostFileModel(Long postId, Long[] files)
    {
        List<PostFileDTO> postFileDTOs = new ArrayList<>();
        for(Long fileId: files)
        {
            PostFileDTO postFileDTO = new PostFileDTO();
            fileDAO.findById(fileId).orElseThrow(() -> new RuntimeException("File doesn't exist."));
            postFileDTO.setFileId(fileId);
            postFileDTO.setPostId(postId);
            postFileDTOs.add(postFileDTO);
        }

        return PostFileMapper.INSTANCE.toListProperty(postFileDTOs);
    }
}
