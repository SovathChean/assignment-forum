package com.example.springassignmentforum.web.controller;

import com.example.springassignmentforum.core.dto.LikeCreationDTO;
import com.example.springassignmentforum.core.dto.LikeDTO;
import com.example.springassignmentforum.core.service.LikeService;
import com.example.springassignmentforum.web.vo.mapper.LikeVOMapper;
import com.example.springassignmentforum.web.vo.request.LikeCreationRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api/like")
public class LikeController {
    @Autowired(required = false)
    private LikeService likeService;
    public LikeController(LikeService likeService)
    {
        this.likeService = likeService;
    }
    @PostMapping
    public ResponseEntity<Object> createLike(@RequestBody LikeCreationRequestVO likeCreationRequestVO)
    {
        LikeCreationDTO likeCreationDTO = LikeVOMapper.INSTANCE.to(likeCreationRequestVO);
        System.out.println(likeCreationDTO.toString());
        LikeDTO likeDTO = likeService.createLike(likeCreationDTO);

        return ResponseEntity.ok(likeDTO);
    }
}
