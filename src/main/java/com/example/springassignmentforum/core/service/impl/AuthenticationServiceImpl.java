package com.example.springassignmentforum.core.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.springassignmentforum.core.common.helper.JwtAlgorithm;
import com.example.springassignmentforum.core.common.helper.JwtCreateToken;
import com.example.springassignmentforum.core.dto.UserDTO;
import com.example.springassignmentforum.core.service.AuthenticationService;
import com.example.springassignmentforum.core.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.apache.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired(required = false)
    private UserService userService;
    @Override
    public Map<String, String> refreshToken(HttpServletRequest request, HttpServletResponse response) {
        final String requestTokenHeader = request.getHeader(AUTHORIZATION);
        Map<String, String> res = new HashMap<>();
        if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer "))
        {
            try
            {
                String token = requestTokenHeader.substring("Bearer ".length());
                Algorithm algorithm = JwtAlgorithm.encrptedAlgorithm();
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(token);
                String username = decodedJWT.getSubject();
                UserDTO user = userService.getUserByName(username);
                res = new JwtCreateToken().createTokens(request, user.getName());

            }catch (Exception e)
            {
                log.error("Error token : {}", e);
                res.put("error", "AccessToken is invalid or expire");
            }
        }
        else
        {
            res.put("error", "AccessToken is invalid or expire");
        }
        return res;
    }
}
