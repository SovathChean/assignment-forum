package com.example.springassignmentforum.core.mapper;

import com.example.springassignmentforum.core.dto.CommentCreationDTO;
import com.example.springassignmentforum.core.dto.CommentDTO;
import com.example.springassignmentforum.core.model.CommentModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CommentMapper {
    public static  final CommentModel INSTANCE = Mappers.getMapper(CommentModel.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "users", ignore = true)
    @Mapping(source = "postId", target = "post.id")
    @Mapping(source = "parentId", target = "parent.id")
    CommentDTO from(CommentCreationDTO commentCreationDTO);
    CommentModel toProperty(CommentDTO commentDTO);
    CommentDTO fromProperty(CommentModel commentModel);
    List<CommentDTO> toListProperty(List<CommentModel> commentModelList);
}
