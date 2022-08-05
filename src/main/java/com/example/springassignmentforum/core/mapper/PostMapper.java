package com.example.springassignmentforum.core.mapper;

import com.example.springassignmentforum.core.dto.*;
import com.example.springassignmentforum.core.model.PostModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {
    public static final PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(source = "userId", target = "user.id")
    PostDTO from(PostCreationDTO postCreationDTO);
    PostModel toProperty(PostDTO postDTO);
    PostDTO fromProperty(PostModel postModel);

    List<PostDTO> fromListProperty(Collection<PostModel> postModels);
    List<PostPaginatedDTO> fromPostEntityToPaginatedResponse(Collection<PostModel> postModels);
    PostDetailsDTO toPostDetails(PostDetailDTO postDetailDTO);
    List<PostModel> fromPostCreationToPostModel(List<PostCreationDTO> postCreationDTOS);
}
