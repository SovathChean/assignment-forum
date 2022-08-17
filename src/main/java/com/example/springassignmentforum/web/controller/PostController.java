package com.example.springassignmentforum.web.controller;

import com.example.springassignmentforum.core.common.exception.ResponseNotFound;
import com.example.springassignmentforum.core.common.filter.PageFilterResult;
import com.example.springassignmentforum.core.dto.PostDTO.*;
import com.example.springassignmentforum.core.dto.userDTO.UserDTO;
import com.example.springassignmentforum.core.mapper.PostImageMapper;
import com.example.springassignmentforum.core.service.CommentService;
import com.example.springassignmentforum.core.service.PostService;
import com.example.springassignmentforum.core.service.UserService;
import com.example.springassignmentforum.web.filter.PostFilterCriteria;
import com.example.springassignmentforum.web.handler.ResponseDataUtils;
import com.example.springassignmentforum.web.handler.ResponseHandler;
import com.example.springassignmentforum.web.handler.ResponseListDataUtils;
import com.example.springassignmentforum.web.handler.ResponsePageUtils;
import com.example.springassignmentforum.web.vo.mapper.PostFileVOMapper;
import com.example.springassignmentforum.web.vo.mapper.PostVOMapper;
import com.example.springassignmentforum.web.vo.request.PostCreationRequestVO;
import com.example.springassignmentforum.web.vo.request.PostUploadImageRequestVO;
import com.example.springassignmentforum.web.vo.response.PostDetailResponseVO;
import com.example.springassignmentforum.web.vo.response.PostResponseVO;
import com.example.springassignmentforum.web.vo.response.PostUploadImageResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;
    @PostMapping(value="/api/posts")
    public ResponseEntity<ResponseDataUtils<PostResponseVO>> createPost(@RequestBody PostCreationRequestVO postCreationRequestVO)
    {
        UserDTO userDTO = userService.getAuthByName();
        postCreationRequestVO.setUserId(userDTO.getId());
        PostCreationDTO postCreationDTO = PostVOMapper.INSTANCE.from(postCreationRequestVO);
        PostDTO postDTO = postService.createPost(userDTO.getId(), postCreationDTO);
        PostResponseVO postResponseVO = PostVOMapper.INSTANCE.to(postDTO);

        return ResponseHandler.responseData("Create Post Successfully", HttpStatus.CREATED, postResponseVO);
    }
    @PostMapping(value="/api/posts/{id}")
    public ResponseEntity<ResponseDataUtils<PostResponseVO>> updatePost(@PathVariable(value="id") Long id, @RequestBody PostCreationRequestVO postCreationRequestVO)
    {
        PostCreationDTO postCreationDTO = PostVOMapper.INSTANCE.from(postCreationRequestVO);
        PostDTO postDTO = postService.updatePost(id, postCreationDTO);
        PostResponseVO postResponseVO = PostVOMapper.INSTANCE.to(postDTO);

        return ResponseHandler.responseData("Update Post Successfully", HttpStatus.OK, postResponseVO);
    }
    @RequestMapping(value="/api/posts/upload-image/{id}", method = RequestMethod.POST)
    public ResponseEntity<ResponseListDataUtils<PostUploadImageResponseVO>> uploadImage(@PathVariable(value="id") Long id, @RequestBody PostUploadImageRequestVO postUploadImageRequestVO)
    {
        PostUploadImageDTO postUploadImageDTO = PostImageMapper.INSTANCE.fromRequestToDTO(postUploadImageRequestVO);
        List<PostFileDTO> postFileDTO = postService.uploadImage(id, postUploadImageDTO);
        List<PostUploadImageResponseVO> postResponseVO = PostFileVOMapper.INSTANCE.fromDtoToResponseVO(postFileDTO);
        return ResponseHandler.responseListData("Upload Image Successfully", HttpStatus.OK, postResponseVO);
    }
    @RequestMapping(value="/api/posts/delete-image/{id}", method = RequestMethod.POST)
    public ResponseEntity<ResponseListDataUtils<PostUploadImageResponseVO>> deleteImage(@PathVariable(value="id") Long id, @RequestBody PostUploadImageRequestVO postUploadImageRequestVO)
    {
        PostUploadImageDTO postUploadImageDTO = PostImageMapper.INSTANCE.fromRequestToDTO(postUploadImageRequestVO);
        List<PostFileDTO> postFileDTO = postService.deleteImage(id, postUploadImageDTO);
        List<PostUploadImageResponseVO> postResponseVO = PostFileVOMapper.INSTANCE.fromDtoToResponseVO(postFileDTO);

        return ResponseHandler.responseListData("Delete Image Successfully", HttpStatus.OK, postResponseVO);
    }
    @GetMapping(value="/api/posts")
    public ResponseEntity<ResponsePageUtils<PostResponseVO>> getAllPost(
            @RequestParam(value="fromDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) String fromDateTime,
            @RequestParam(value ="toDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) String toDateTime,
            PostFilterCriteria postFilterCriteria)
    {
        postFilterCriteria.setFromDateTime(this.convertStringToLocalDateTime(fromDateTime));
        postFilterCriteria.setToDateTime(this.convertStringToLocalDateTime(toDateTime));
        PageFilterResult<PostPaginatedDTO> page = postService.getAllPost(postFilterCriteria);
        PageFilterResult<PostResponseVO> res = new PageFilterResult<>(page.getTotalRows(), PostVOMapper.INSTANCE.fromPostPaginatedToPostResponseVO(page.getPageData()));

        return ResponseHandler.responsePagination(null, HttpStatus.OK, res);
    }
    @GetMapping(value="/api/posts/{id}")
    public ResponseEntity<ResponseDataUtils<PostDetailResponseVO>> getPostById(@PathVariable(value="id") Long id)
    {
        PostDetailsDTO postDetailsDTO = postService.getPostDetail(id);
        PostDetailResponseVO postDetailResponseVO = PostVOMapper.INSTANCE.toPostDetail(postDetailsDTO);

        return ResponseHandler.responseData(null, HttpStatus.OK, postDetailResponseVO);
    }
    @GetMapping(value="/api/posts/owner")
    public ResponseEntity<ResponsePageUtils<PostResponseVO>> getPostByCreatorId(
            @RequestParam(value="fromDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) String fromDateTime,
            @RequestParam(value ="toDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) String toDateTime,
            PostFilterCriteria postFilterCriteria
    )
    {
        UserDTO userDTO = userService.getAuthByName();
        postFilterCriteria.setCreatorId(userDTO.getId());
        postFilterCriteria.setFromDateTime(this.convertStringToLocalDateTime(fromDateTime));
        postFilterCriteria.setToDateTime(this.convertStringToLocalDateTime(toDateTime));
        PageFilterResult<PostPaginatedDTO> page = postService.getAllPost(postFilterCriteria);
        PageFilterResult<PostResponseVO> res = new PageFilterResult<>(page.getTotalRows(), PostVOMapper.INSTANCE.fromPostPaginatedToPostResponseVO(page.getPageData()));

        return ResponseHandler.responsePagination(null, HttpStatus.OK, res);
    }
    public LocalDateTime convertStringToLocalDateTime(String dateTime)
    {
        if(dateTime == null)
            return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime date = LocalDateTime.parse(dateTime, formatter);

        return date;
    }
}
