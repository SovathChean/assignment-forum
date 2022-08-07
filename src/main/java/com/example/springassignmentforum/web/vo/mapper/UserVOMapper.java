package com.example.springassignmentforum.web.vo.mapper;


import com.example.springassignmentforum.core.dto.userDTO.UserCreationDTO;
import com.example.springassignmentforum.core.dto.userDTO.UserDTO;
import com.example.springassignmentforum.web.vo.request.UserCreationRequestVO;
import com.example.springassignmentforum.web.vo.response.UserResponseVO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserVOMapper {
    public static final UserVOMapper INSTANCE = Mappers.getMapper(UserVOMapper.class);
    UserCreationDTO from (UserCreationRequestVO userCreationRequestVO);
    UserResponseVO to (UserDTO userDTO);
    List<UserResponseVO> toList(List<UserDTO> userDTOList);
}
