package com.example.springassignmentforum.web.vo.mapper;

import com.example.springassignmentforum.core.dto.PostCreationDTO;
import com.example.springassignmentforum.core.dto.PostDTO;
import com.example.springassignmentforum.core.dto.PostDetailDTO;
import com.example.springassignmentforum.core.mapper.PostMapper;
import com.example.springassignmentforum.web.vo.request.PostCreationRequestVO;
import com.example.springassignmentforum.web.vo.response.PostDetailResponseVO;
import com.example.springassignmentforum.web.vo.response.PostResponseVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostVOMapper {
    public static final PostVOMapper INSTANCE = Mappers.getMapper(PostVOMapper.class);
    PostCreationDTO from(PostCreationRequestVO postCreationRequestVO);
    PostResponseVO to(PostDTO postDTO);
    List<PostResponseVO> toList(List<PostDTO> postDTOList);
//    @Mapping(target = "createdAt", ignore = true)
//    @Mapping(target = "updatedAt", ignore = true)
//    @Mapping(target = "images", ignore = true)
//    @Mapping(target = "comments", ignore = true)
    PostDetailResponseVO toPostDetail(PostDetailDTO postDetailDTO);
}
