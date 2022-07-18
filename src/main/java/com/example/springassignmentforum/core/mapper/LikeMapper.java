package com.example.springassignmentforum.core.mapper;

import com.example.springassignmentforum.core.dto.LikeCreationDTO;
import com.example.springassignmentforum.core.dto.LikeDTO;
import com.example.springassignmentforum.core.model.LikeModel;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface LikeMapper {
    public static final LikeMapper INSTANCE = Mappers.getMapper(LikeMapper.class);
    LikeDTO from(LikeCreationDTO likeCreationDTO);
    LikeModel toProperty(LikeDTO likeDTO);
    LikeDTO fromProperty(LikeModel likeModel);
    List<LikeDTO> fromListProperty(List<LikeModel> likeModelList);
}
