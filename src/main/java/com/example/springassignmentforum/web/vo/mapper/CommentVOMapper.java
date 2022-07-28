package com.example.springassignmentforum.web.vo.mapper;

import com.example.springassignmentforum.core.dto.CommentCreationDTO;
import com.example.springassignmentforum.core.dto.CommentDTO;
import com.example.springassignmentforum.core.dto.PostCommentDTO;
import com.example.springassignmentforum.web.vo.request.CommentCreationRequestVO;
import com.example.springassignmentforum.web.vo.response.CommentResponseVO;
import com.example.springassignmentforum.web.vo.response.PostCommentResponseVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CommentVOMapper {
    public static final CommentVOMapper INSTANCE = Mappers.getMapper(CommentVOMapper.class);
    CommentCreationDTO to(CommentCreationRequestVO commentCreationRequestVO);
    CommentResponseVO from(CommentDTO commentDTO);
    List<CommentResponseVO> toList(List<CommentDTO> commentDTO);
    List<PostCommentResponseVO> toListPostCommentResponse(List<PostCommentDTO> postCommentDTOS);
}
