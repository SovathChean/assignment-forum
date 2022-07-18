package com.example.springassignmentforum.core.mapper;


import com.example.springassignmentforum.core.dto.PostLikeDTO;
import com.example.springassignmentforum.core.model.PostLikeModel;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PostLikeMapper {
    public static final PostLikeMapper INSTANCE = Mappers.getMapper(PostLikeMapper.class);

    PostLikeDTO fromProperty(PostLikeModel postLikeModel);
    PostLikeModel toProperty(PostLikeDTO postLikeDTO);
}
