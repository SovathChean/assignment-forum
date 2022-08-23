package com.example.springassignmentforum.core.config;

import com.example.springassignmentforum.core.common.helper.JwtCreateToken;
import com.example.springassignmentforum.core.service.AuthenticationService;
import com.example.springassignmentforum.web.vo.response.OAuthTokenResponseVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
public class JwtUtilFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final AuthenticationService authenticationService;
    public JwtUtilFilter(AuthenticationManager authenticationManager, AuthenticationService authenticationService)
    {
        this.authenticationManager = authenticationManager;
        this.authenticationService = authenticationService;
    }
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User user= (User) authResult.getPrincipal();
        String uniqueKey = UUID.randomUUID().toString();

        OAuthTokenResponseVO tokens = new JwtCreateToken().createTokens(request, user.getUsername(), uniqueKey);
        response.setContentType(APPLICATION_JSON_VALUE);
        authenticationService.storeTokenUniqueKey(uniqueKey, false);

        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
       String username = request.getParameter("username");
       String password = request.getParameter("password");
       log.info("Username is {}", username);
       log.info("Password is {}", password);
       UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
       return authenticationManager.authenticate(usernamePasswordAuthenticationToken);
    }


}
