package com.example.springassignmentforum.web.controller;

import com.example.springassignmentforum.core.service.AuthenticationService;
import com.example.springassignmentforum.web.handler.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService)
    {
        this.authenticationService = authenticationService;
    }
    @GetMapping(value="/api/refreshToken")
    public ResponseEntity<Object> getRefreshToken(HttpServletRequest request, HttpServletResponse response)
    {
        Map<String, String> token = authenticationService.refreshToken(request, response);

        return ResponseHandler.responseWithObject(null, HttpStatus.OK, token);
    }
}
