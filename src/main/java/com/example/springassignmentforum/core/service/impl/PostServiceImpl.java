package com.example.springassignmentforum.core.service.impl;

import com.example.springassignmentforum.core.common.filter.PageFilterResult;
import com.example.springassignmentforum.core.dao.CommentDAO;
import com.example.springassignmentforum.core.dao.FileDAO;
import com.example.springassignmentforum.core.dao.PostDAO;
import com.example.springassignmentforum.core.dao.PostFileDAO;
import com.example.springassignmentforum.core.dto.PostDTO.*;
import com.example.springassignmentforum.core.mapper.PostFileMapper;
import com.example.springassignmentforum.core.mapper.PostMapper;
import com.example.springassignmentforum.core.model.PostFileModel;
import com.example.springassignmentforum.core.model.PostModel;
import com.example.springassignmentforum.core.service.CommentService;
import com.example.springassignmentforum.core.service.LikeService;
import com.example.springassignmentforum.core.service.PostService;

import com.example.springassignmentforum.core.service.UserService;
import com.example.springassignmentforum.web.filter.PostFilterCriteria;
import com.example.springassignmentforum.web.vo.mapper.CommentVOMapper;
import com.example.springassignmentforum.web.vo.mapper.FileVOMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class PostServiceImpl implements PostService {
    @Autowired(required = false)
    private PostDAO postDAO;
    @Autowired(required = false)
    private PostFileDAO postFileDAO;
    @Autowired(required = false)
    private CommentDAO commentDAO;
    @Autowired(required = false)
    private CommentService commentService;
    @Autowired(required = false)
    private UserService userService;
    @Autowired(required = false)
    private LikeService likeService;
    @Autowired(required = false)
    private FileDAO fileDAO;
    @Autowired
    private Environment env;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public PostDTO createPost(Long userId, PostCreationDTO postCreationDTO) {
        PostDTO postDTO = PostMapper.INSTANCE.from(postCreationDTO);
        PostModel postModel = PostMapper.INSTANCE.toProperty(postDTO);
        postModel.setCreatedAt(LocalDateTime.now());
        try {
            postDAO.save(postModel);
            log.info("post is created {}:", postModel);
        }
        catch (Exception e)
        {
            log.error("Post creations is error {}:", e.getMessage());
            throw e;
        }

        return PostMapper.INSTANCE.fromProperty(postModel);
    }

    @Override
    public PostDTO getPostById(long id)
    {
        PostModel postModel = postDAO.findById(id).orElseThrow(() -> new RuntimeException("Post Not Found."));

        return PostMapper.INSTANCE.fromProperty(postModel);
    }
    @Override
    public PostDetailsDTO getPostDetail(Long postId) {
        PostDetailsDTO postDetailsDTO = new PostDetailsDTO();
        PostDetailDTO postDetail = postDAO.findPostDetails(postId);
        List<PostCommentDTO> postCommentDTO = commentService.getCommentByPostID(postId);
        List<PostFileImageDTO> postFileImageDTOS = this.getPostFileImageByPostId(postId);
        Boolean postLikeCount = likeService.isLike(userService.getAuthByName().getId(), postId);
        postDetailsDTO = PostMapper.INSTANCE.toPostDetails(postDetail);
        postDetailsDTO.setIsLike(postLikeCount);
        postDetailsDTO.setImages(FileVOMapper.INSTANCE.toListPostFile(postFileImageDTOS));
        postDetailsDTO.setComments(CommentVOMapper.INSTANCE.toListPostCommentResponse(postCommentDTO));

        return postDetailsDTO;
    }
    @Override
    public List<PostFileImageDTO> getPostFileImageByPostId(Long postId) {
        String localhost = env.getProperty("spring.pathLink");
        String path = localhost+"/api/file/";
        List<PostFileImageDTO> postFileImageDTOS = postFileDAO.findFileDetailByPostId(postId, path);

        return postFileImageDTOS;
    }

    @Override
    public PostDTO updatePost(Long postId, PostCreationDTO postCreationDTO) {
        PostModel postModel = postDAO.findById(postId).orElseThrow(() -> new RuntimeException("File doesn't exist."));;
        postModel.setUpdatedAt(LocalDateTime.now());
        postModel.setDescription(postCreationDTO.getDescription());
        postModel.setSubject(postCreationDTO.getSubject());
        postDAO.save(postModel);

        return PostMapper.INSTANCE.fromProperty(postModel);
    }
    @Override
    public List<PostFileDTO> uploadImage(Long postId, PostUploadImageDTO postUploadImageDTO) {

        List<PostFileModel> postFileModels = this.getPostFileModel(postId, postUploadImageDTO.getPhotos());
        postFileDAO.saveAll(postFileModels);

        return PostFileMapper.INSTANCE.fromListProperty(postFileModels);
    }
    @Override
    public List<PostFileDTO> deleteImage(Long postId, PostUploadImageDTO postUploadImageDTO) {
        List<PostFileModel> postFileModels = this.getDeletePostFile(postUploadImageDTO.getPhotos());
        postFileDAO.deleteAll(postFileModels);


        return PostFileMapper.INSTANCE.fromListProperty(postFileModels);
    }


    @Override
    public PageFilterResult<PostPaginatedDTO> getAllPost(PostFilterCriteria postFilterCriteria) {
        List<PostPaginatedDTO> data = new ArrayList<>();
        Long totalRow = (long) 0;
        if(!postFilterCriteria.getPaginated())
        {
            data = PostMapper.INSTANCE.fromPostEntityToPaginatedResponse(postDAO.findAll());
            totalRow = postDAO.count();
            return new PageFilterResult<PostPaginatedDTO>(totalRow, data);
        }
        Integer offset =( postFilterCriteria.getPageNo() - 1 ) * postFilterCriteria.getPageSize();
        List<PostPaginatedDTO> postDTOList = postDAO.findAllPostFilters(postFilterCriteria.getSearch(), postFilterCriteria.getFromDateTime(), postFilterCriteria.getToDateTime(), postFilterCriteria.getCreatorId(), postFilterCriteria.getPageSize(), offset);
        PostCountFilterDto postCountFilterDto = postDAO.findTotalRowPostFilters(postFilterCriteria.getSearch(), postFilterCriteria.getFromDateTime(), postFilterCriteria.getToDateTime(), postFilterCriteria.getCreatorId());
        return new PageFilterResult<>(postCountFilterDto.getTotalRow(), postDTOList);
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
        log.info("Get Store file");
        return PostFileMapper.INSTANCE.toListProperty(postFileDTOs);
    }
    public List<PostFileModel> getDeletePostFile(Long[] files)
    {
        List<PostFileModel> postFiles = new ArrayList<>();
        for(Long fileId: files)
        {
            var postFileModels = postFileDAO.findByFileId(fileId);
            if(postFileModels != null)
                postFiles.addAll(postFileModels);
        }
        return postFiles;
    }
}
