package com.example.springassignmentforum.core.mapper;

import com.example.springassignmentforum.core.dto.CommentCreationDTO;
import com.example.springassignmentforum.core.dto.CommentDTO;
import com.example.springassignmentforum.core.dto.PostCommentDTO;
import com.example.springassignmentforum.core.model.CommentModel;
import com.example.springassignmentforum.web.vo.response.CommentResponseVO;
import com.example.springassignmentforum.web.vo.response.PostCommentResponseVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CommentMapper {
    public static  final CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(source = "postId", target = "posts.id")
    @Mapping(source = "userId", target = "users.id")
    CommentDTO from(CommentCreationDTO commentCreationDTO);
    CommentModel toProperty(CommentDTO commentDTO);
    CommentDTO fromProperty(CommentModel commentModel);
    List<CommentDTO> toListProperty(List<CommentModel> commentModelList);
}
