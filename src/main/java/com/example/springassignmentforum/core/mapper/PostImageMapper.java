package com.example.springassignmentforum.core.mapper;

import com.example.springassignmentforum.core.dto.PostLikeDTO;
import com.example.springassignmentforum.core.dto.PostUploadImageDTO;
import com.example.springassignmentforum.core.model.PostLikeModel;
import com.example.springassignmentforum.web.vo.request.PostUploadImageRequestVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PostImageMapper {
    public static final PostImageMapper INSTANCE = Mappers.getMapper(PostImageMapper.class);

    PostUploadImageDTO fromRequestToDTO(PostUploadImageRequestVO postUploadImageRequestVO);

}
