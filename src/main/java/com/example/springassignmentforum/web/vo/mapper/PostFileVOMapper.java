package com.example.springassignmentforum.web.vo.mapper;

import com.example.springassignmentforum.core.dto.PostDTO.PostFileDTO;
import com.example.springassignmentforum.web.vo.response.PostUploadImageResponseVO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostFileVOMapper {
    public static final  PostFileVOMapper INSTANCE = Mappers.getMapper(PostFileVOMapper.class);
    List<PostUploadImageResponseVO> fromDtoToResponseVO(List<PostFileDTO> postFileDTOList);
}
