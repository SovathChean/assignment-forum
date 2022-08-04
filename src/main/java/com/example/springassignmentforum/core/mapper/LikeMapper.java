package com.example.springassignmentforum.core.mapper;

import com.example.springassignmentforum.core.dto.LikeCreationDTO;
import com.example.springassignmentforum.core.dto.LikeDTO;
import com.example.springassignmentforum.core.model.LikeModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LikeMapper {
    public static final LikeMapper INSTANCE = Mappers.getMapper(LikeMapper.class);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(source = "userId", target = "users.id")
    @Mapping(source = "postId", target = "posts.id")
    LikeDTO from(LikeCreationDTO likeCreationDTO);
    LikeModel toProperty(LikeDTO likeDTO);
    LikeDTO fromProperty(LikeModel likeModel);
    List<LikeDTO> fromListProperty(List<LikeModel> likeModelList);
}
