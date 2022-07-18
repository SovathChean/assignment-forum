package com.example.springassignmentforum.web.vo.mapper;

import com.example.springassignmentforum.core.dto.LikeCreationDTO;
import com.example.springassignmentforum.core.dto.LikeDTO;
import com.example.springassignmentforum.core.model.LikeModel;
import com.example.springassignmentforum.web.vo.request.LikeCreationRequestVO;
import com.example.springassignmentforum.web.vo.response.LikeResponseVO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface LikeVOMapper {
    public static final LikeVOMapper INSTANCE = Mappers.getMapper(LikeVOMapper.class);

    LikeCreationDTO to(LikeCreationRequestVO likeCreationRequestVO);

    LikeResponseVO from(LikeDTO likeDTO);
    List<LikeResponseVO> fromListProperty(List<LikeDTO> likeDTOList);
}
