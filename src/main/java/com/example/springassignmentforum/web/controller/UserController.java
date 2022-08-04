package com.example.springassignmentforum.web.controller;

import com.example.springassignmentforum.core.common.helper.JwtCreateToken;
import com.example.springassignmentforum.core.dto.UserCreationDTO;
import com.example.springassignmentforum.core.dto.UserDTO;
import com.example.springassignmentforum.core.service.AuthenticationService;
import com.example.springassignmentforum.core.service.UserService;
import com.example.springassignmentforum.web.handler.ResponseHandler;
import com.example.springassignmentforum.web.vo.mapper.UserVOMapper;
import com.example.springassignmentforum.web.vo.request.UserCreationRequestVO;
import com.example.springassignmentforum.web.vo.response.UserResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationService authenticationService;
    public UserController(UserService userService, AuthenticationService authenticationService)
    {
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    @RequestMapping(value="/api/register", method = RequestMethod.POST)
    public ResponseEntity<Object> register(HttpServletRequest request, HttpServletResponse httpRes, @RequestBody UserCreationRequestVO userCreationRequestVO)
    {
        UserCreationDTO userCreationDTO = UserVOMapper.INSTANCE.from(userCreationRequestVO);
        UserDTO register = userService.register(userCreationDTO);
        UserResponseVO response = UserVOMapper.INSTANCE.to(register);
        String uniqueKey = UUID.randomUUID().toString();
        Map<String, String> tokens = new JwtCreateToken().createTokens(request, response.getName(), uniqueKey);
        authenticationService.storeTokenUniqueKey(uniqueKey, false);

        return ResponseHandler.responseWithObject("Create User Successfully!", HttpStatus.CREATED, tokens);
    }
    @RequestMapping(value="/api/users/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getUserById(@PathVariable(value="id") Long id)
    {
        UserDTO userDTO = userService.findById(id);
        UserResponseVO res = UserVOMapper.INSTANCE.to(userDTO);
        return ResponseHandler.responseWithObject(null, HttpStatus.OK, res);
    }
    @RequestMapping(value="/api/users", method = RequestMethod.GET)
    public ResponseEntity<Object> getAllUsers()
    {
        List<UserDTO> userDTO = userService.findAll();
        var res = UserVOMapper.INSTANCE.toList(userDTO);

        return ResponseHandler.responseWithObject(null, HttpStatus.OK, res);
    }
}
