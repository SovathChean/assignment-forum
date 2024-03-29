package com.example.springassignmentforum.core.service.impl;

import com.example.springassignmentforum.core.dao.UserDAO;
import com.example.springassignmentforum.core.dto.userDTO.UserCreationDTO;
import com.example.springassignmentforum.core.dto.userDTO.UserDTO;
import com.example.springassignmentforum.core.mapper.UserMapper;
import com.example.springassignmentforum.core.model.UserModel;
import com.example.springassignmentforum.core.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
	@Autowired(required = false)
	public UserDAO userDao;
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDTO register(UserCreationDTO userCreationDTO) {
		UserDTO userDTO = UserMapper.INSTANCE.from(userCreationDTO);
		UserModel userModel = UserMapper.INSTANCE.toProperty(userDTO);
		userModel.setPassword(new BCryptPasswordEncoder().encode(userModel.getPassword()));
        userDao.save(userModel);
		log.info("Save User");
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

	@Override
	public UserDTO getUserByName(String name) {
		UserModel user = userDao.findUserByName(name);

		return UserMapper.INSTANCE.fromProperty(user);
	}

	@Override
	public UserDTO getAuthByName() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserModel userModel = userDao.findUserByName(auth.getPrincipal().toString());
		return UserMapper.INSTANCE.fromProperty(userModel);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserModel userModel = userDao.findUserByName(username);
		if(userModel == null)
		{
			log.error("User not found in the database");
		}
		else
		{
			log.info("User found in the database {}: ", userModel);
		}
		return new org.springframework.security.core.userdetails.User(userModel.getName(), userModel.getPassword(), new ArrayList<>());
	}
}
