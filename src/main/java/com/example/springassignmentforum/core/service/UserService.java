package com.example.springassignmentforum.core.service;

import com.example.springassignmentforum.core.dto.userDTO.UserCreationDTO;
import com.example.springassignmentforum.core.dto.userDTO.UserDTO;

import java.util.List;

public interface UserService {
	
	UserDTO register(UserCreationDTO userCreationDTO);
	UserDTO findById(Long id);
	List<UserDTO> findAll();
	UserDTO getUserByName(String name);
	UserDTO getAuthByName();
}
