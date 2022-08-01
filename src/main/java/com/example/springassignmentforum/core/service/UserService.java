package com.example.springassignmentforum.core.service;

import com.example.springassignmentforum.core.dto.UserCreationDTO;
import com.example.springassignmentforum.core.dto.UserDTO;

import java.util.List;

public interface UserService {
	
	UserDTO register(UserCreationDTO userCreationDTO);
	UserDTO findById(Long id);
	List<UserDTO> findAll();
	UserDTO getUserByName(String name);
	UserDTO getAuthByName();
}
