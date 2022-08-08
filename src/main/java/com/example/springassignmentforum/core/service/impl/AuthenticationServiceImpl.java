package com.example.springassignmentforum.core.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.springassignmentforum.core.common.helper.JwtAlgorithm;
import com.example.springassignmentforum.core.common.helper.JwtCreateToken;
import com.example.springassignmentforum.core.dao.OAuthTokenDAO;
import com.example.springassignmentforum.core.dto.userDTO.UserDTO;
import com.example.springassignmentforum.core.model.OAuthTokenModel;
import com.example.springassignmentforum.core.service.AuthenticationService;
import com.example.springassignmentforum.core.service.UserService;
import com.example.springassignmentforum.web.vo.response.OAuthTokenResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.*;

import static org.apache.http.HttpHeaders.AUTHORIZATION;

@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired(required = false)
    private UserService userService;
    @Autowired(required = false)
    private OAuthTokenDAO oAuthTokenDAO;
    @Override
    public OAuthTokenResponseVO refreshToken(HttpServletRequest request, HttpServletResponse response, String uniqueKey) {
        final String requestTokenHeader = request.getHeader(AUTHORIZATION);
        OAuthTokenResponseVO oauthToken = new OAuthTokenResponseVO();
        Map<String, String> res = new HashMap<>();
        if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer "))
        {
            try
            {
                String token = requestTokenHeader.substring("Bearer ".length());
                DecodedJWT decodedJWT = getDecodedJWT(token);
                String username = decodedJWT.getSubject();
                //HasRevokeToken
                hasRevokeToken(decodedJWT.getClaim("tokenKey").asString());
                UserDTO user = userService.getUserByName(username);
                oauthToken = new JwtCreateToken().createTokens(request, user.getName(), uniqueKey);
                log.info("Get refresh token");
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
        return oauthToken;
    }
    @Override
    public void storeTokenUniqueKey(String unqiueKey, Boolean isRevoke)
    {
        OAuthTokenModel oAuthTokenModel = new OAuthTokenModel();
        oAuthTokenModel.setUniqueKey(unqiueKey);
        oAuthTokenModel.setRequestAt(LocalDateTime.now());
        oAuthTokenDAO.save(oAuthTokenModel);
    }

    @Override
    public Boolean isRevoke(String uniqueKey) {
        return oAuthTokenDAO.findByUniqueKey(uniqueKey) == null;
    }

    public void hasRevokeToken(String unqiueKey)
    {
        OAuthTokenModel oAuthTokenModel = oAuthTokenDAO.findByUniqueKey(unqiueKey);

        oAuthTokenDAO.delete(oAuthTokenModel);
    }
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response)
    {
        final String requestTokenHeader = request.getHeader(AUTHORIZATION);
        Map<String, String> res = new HashMap<>();
        if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer "))
        {
            try
            {
                String token = requestTokenHeader.substring("Bearer ".length());
                DecodedJWT decodedJWT = getDecodedJWT(token);
                String username = decodedJWT.getSubject();
                //HasRevokeToken
                hasRevokeToken(decodedJWT.getClaim("tokenKey").asString());
                UserDTO user = userService.getUserByName(username);
                log.info("user has logout.");
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
    }
    public DecodedJWT getDecodedJWT(String token)
    {
        Algorithm algorithm = JwtAlgorithm.encrptedAlgorithm();
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);

        return decodedJWT;
    }

}
