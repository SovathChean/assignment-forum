package com.example.springassignmentforum.core.mapper;


import com.example.springassignmentforum.core.dto.PostDTO.PostLikeDTO;
import com.example.springassignmentforum.core.model.PostLikeModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PostLikeMapper {
    public static final PostLikeMapper INSTANCE = Mappers.getMapper(PostLikeMapper.class);
    @Mapping(target = "postId", source = "post.id")
    PostLikeDTO fromProperty(PostLikeModel postLikeModel);
    @Mapping(source = "postId", target = "post.id")
    @Mapping(target = "id", ignore = true)
    PostLikeModel toProperty(PostLikeDTO postLikeDTO);
}
