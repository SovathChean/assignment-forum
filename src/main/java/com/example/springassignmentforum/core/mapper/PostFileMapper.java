package com.example.springassignmentforum.core.mapper;

import com.example.springassignmentforum.core.dto.PostDTO.PostFileDTO;
import com.example.springassignmentforum.core.model.PostFileModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostFileMapper {
    PostFileMapper INSTANCE = Mappers.getMapper(PostFileMapper.class);
    @Mapping(source = "postId", target = "post.id")
    @Mapping(source = "fileId", target = "file.id")
    @Mapping(target = "id", ignore = true)
    PostFileModel to(PostFileDTO postFileDTO);
    @Mapping(source = "postId", target = "post.id")
    @Mapping(source = "file", target = "file.id")
    List<PostFileModel> toListProperty(List<PostFileDTO> postFileDTOList);
    @Mapping(target = "postId", source = "post.id")
    @Mapping(target = "file", source = "file.id")
    List<PostFileDTO> fromListProperty(List<PostFileModel> postFileModels);
}
