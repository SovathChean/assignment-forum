package com.example.springassignmentforum.core.common.authentication;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.springassignmentforum.core.common.helper.JwtAlgorithm;
import com.example.springassignmentforum.core.service.AuthenticationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.apache.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
public class AuthorizationFilter extends OncePerRequestFilter {
    @Autowired
    private final AuthenticationService authenticationService;
    public AuthorizationFilter(AuthenticationService authenticationService)
    {
        this.authenticationService = authenticationService;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String requestTokenHeader = request.getHeader(AUTHORIZATION);
        if(request.getServletPath().equals("/api/login"))
            filterChain.doFilter(request, response);
        else
        {
            if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer "))
            {
                try
                {
                    String token = requestTokenHeader.substring("Bearer ".length());
                    Algorithm algorithm = JwtAlgorithm.encrptedAlgorithm();
                    JWTVerifier verifier = JWT.require(algorithm).build();
                    DecodedJWT decodedJWT = verifier.verify(token);
                    String username = decodedJWT.getSubject();
                    String uniqueKey = decodedJWT.getClaim("tokenKey").asString();
                    if(!authenticationService.isRevoke(uniqueKey))
                    {
                        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new
                                UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
                        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                        filterChain.doFilter(request, response);
                        log.info("User is successfully login.");
                    }
                    else
                    {
                        filterChain.doFilter(request, response);
                    }

                }catch (Exception e)
                {
                    Map<String, String> error = new HashMap<>();
                    error.put("error", e.getMessage());
                    response.setContentType(APPLICATION_JSON_VALUE);
                    if(request.getServletPath().equals("/api/login"))
                    {
                        response.setStatus(401);
                    }
                    else
                    {
                        response.setStatus(500);
                    }
                    new ObjectMapper().writeValue(response.getOutputStream(), error);
                }
            }else
            {
                filterChain.doFilter(request, response);
            }
        }
    }
}
