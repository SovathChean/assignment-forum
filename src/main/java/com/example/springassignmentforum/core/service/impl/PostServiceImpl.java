package com.example.springassignmentforum.core.service.impl;

import com.example.springassignmentforum.core.dao.FileDAO;
import com.example.springassignmentforum.core.dao.PostDAO;
import com.example.springassignmentforum.core.dao.PostFileDAO;
import com.example.springassignmentforum.core.dto.PostCreationDTO;
import com.example.springassignmentforum.core.dto.PostDTO;
import com.example.springassignmentforum.core.dto.PostFileDTO;
import com.example.springassignmentforum.core.mapper.PostFileMapper;
import com.example.springassignmentforum.core.mapper.PostMapper;
import com.example.springassignmentforum.core.model.FileModel;
import com.example.springassignmentforum.core.model.PostFileModel;
import com.example.springassignmentforum.core.model.PostModel;
import com.example.springassignmentforum.core.service.FileService;
import com.example.springassignmentforum.core.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired(required = false)
    private PostDAO postDAO;
    @Autowired(required = false)
    private PostFileDAO postFileDAO;
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
    public List<PostDTO> getAllPost() {
        var postDAOAll = postDAO.findAll();

        return postDAOAll.stream().map(post -> PostMapper.INSTANCE.fromProperty(post)).collect(Collectors.toList());
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
    public List<PostFileModel> getPostFileModel(Long postId, Long[] files)
    {
        List<PostFileDTO> postFileDTOs = new ArrayList<>();
        System.out.println(files);
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
