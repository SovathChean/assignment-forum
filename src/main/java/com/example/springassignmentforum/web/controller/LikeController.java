package com.example.springassignmentforum.web.controller;

import com.example.springassignmentforum.core.dto.LikeCreationDTO;
import com.example.springassignmentforum.core.dto.LikeDTO;
import com.example.springassignmentforum.core.dto.UserDTO;
import com.example.springassignmentforum.core.service.LikeService;
import com.example.springassignmentforum.core.service.UserService;
import com.example.springassignmentforum.web.handler.ResponseHandler;
import com.example.springassignmentforum.web.vo.mapper.LikeVOMapper;
import com.example.springassignmentforum.web.vo.request.LikeCreationRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api/like")
public class LikeController {
    @Autowired(required = false)
    private LikeService likeService;
    @Autowired(required = false)
    private UserService userService;
    public LikeController(LikeService likeService)
    {
        this.likeService = likeService;
    }
    @PostMapping
    public ResponseEntity<Object> createLike( @RequestBody LikeCreationRequestVO likeCreationRequestVO)
    {
        UserDTO userDTO = userService.getAuthByName();
        likeCreationRequestVO.setUserId(userDTO.getId());
        LikeCreationDTO likeCreationDTO = LikeVOMapper.INSTANCE.to(likeCreationRequestVO);
        LikeDTO likeDTO = likeService.createLike(likeCreationDTO);

        return ResponseHandler.responseWithMsg("Like creation successfully.", HttpStatus.OK);
    }
}
