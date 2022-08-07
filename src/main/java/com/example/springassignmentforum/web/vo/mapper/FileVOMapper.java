package com.example.springassignmentforum.web.vo.mapper;

import com.example.springassignmentforum.core.dto.PostDTO.PostFileImageDTO;
import com.example.springassignmentforum.web.vo.response.PostFileResponseVO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface FileVOMapper {
    public final static  FileVOMapper INSTANCE = Mappers.getMapper(FileVOMapper.class);
    List<PostFileResponseVO> toListPostFile(List<PostFileImageDTO> postFileImageDTOS);
}
