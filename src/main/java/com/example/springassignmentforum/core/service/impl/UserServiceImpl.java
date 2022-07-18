package com.example.springassignmentforum.core.service.impl;

import com.example.springassignmentforum.core.dao.UserDAO;
import com.example.springassignmentforum.core.dto.UserCreationDTO;
import com.example.springassignmentforum.core.dto.UserDTO;
import com.example.springassignmentforum.core.mapper.UserMapper;
import com.example.springassignmentforum.core.model.UserModel;
import com.example.springassignmentforum.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
	@Autowired(required = false)
	public UserDAO userDao;
	@Override
	public UserDTO register(UserCreationDTO userCreationDTO) {
		UserDTO userDTO = UserMapper.INSTANCE.from(userCreationDTO);
		UserModel userModel = UserMapper.INSTANCE.toProperty(userDTO);
        userDao.save(userModel);
		return userDTO;
	}

	@Override
	public UserDTO findById(Long id) {
		UserModel user = userDao.findUserById(id);
		return UserMapper.INSTANCE.fromProperty(user);
	}

	@Override
	public List<UserDTO> findAll() {
		var users = userDao.findAll();

		return users.stream().map(user -> UserMapper.INSTANCE.fromProperty(user)).collect(Collectors.toList());
	}
}
