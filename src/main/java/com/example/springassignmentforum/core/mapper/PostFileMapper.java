package com.example.springassignmentforum.core.mapper;

import com.example.springassignmentforum.core.dto.PostFileDTO;
import com.example.springassignmentforum.core.dto.PostFileImageDTO;
import com.example.springassignmentforum.core.model.PostFileModel;
import com.example.springassignmentforum.web.vo.response.PostFileResponseVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PostFileMapper {
    public final static  PostFileMapper INSTANCE = Mappers.getMapper(PostFileMapper.class);
    @Mapping(source = "postId", target = "post.id")
    @Mapping(source = "fileId", target = "file.id")
    @Mapping(target = "id", ignore = true)
    PostFileModel to(PostFileDTO postFileDTO);
    @Mapping(source = "postId", target = "post.id")
    @Mapping(source = "file", target = "file.id")
    @Mapping(target = "id", ignore = true)
    List<PostFileModel> toListProperty(List<PostFileDTO> postFileDTOList);
}
