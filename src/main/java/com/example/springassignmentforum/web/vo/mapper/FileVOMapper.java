package com.example.springassignmentforum.web.vo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface FileVOMapper {
    public final static  FileVOMapper INSTANCE = Mappers.getMapper(FileVOMapper.class);

}
