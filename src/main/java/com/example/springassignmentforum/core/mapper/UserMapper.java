package com.example.springassignmentforum.core.mapper;

import com.example.springassignmentforum.core.dto.UserCreationDTO;
import com.example.springassignmentforum.core.dto.UserDTO;
import com.example.springassignmentforum.core.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
    public static final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", ignore = true)
    UserDTO from(UserCreationDTO userCreationDTO);

    UserModel toProperty(UserDTO userDTO);

    UserDTO fromProperty(UserModel userModel);
}
