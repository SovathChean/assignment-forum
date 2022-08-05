package com.example.springassignmentforum.core.common.helper;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.springassignmentforum.core.service.AuthenticationService;
import com.example.springassignmentforum.core.service.impl.AuthenticationServiceImpl;
import com.example.springassignmentforum.web.vo.response.OAuthTokenResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JwtCreateToken {
    private final Integer refreshTokenLife = 10*60*24*60*1000;//60days
    private final Integer accessTokenLife = 10*60*1000; //10min

    public OAuthTokenResponseVO createTokens(HttpServletRequest request, String username, String uniqueKey)
    {

        Algorithm algorithm = JwtAlgorithm.encrptedAlgorithm();
        String access_token = JWT.create()
                .withSubject(username)
                .withClaim("tokenKey", uniqueKey)
                .withExpiresAt(new Date(System.currentTimeMillis() + accessTokenLife)) //10min
                .withIssuer(request.getRequestURL().toString())
                .sign(algorithm);
        String refresh_token = JWT.create()
                .withSubject(username)
                .withClaim("tokenKey", uniqueKey)
                .withExpiresAt(new Date(System.currentTimeMillis() + refreshTokenLife)) //60days
                .withIssuer(request.getRequestURL().toString())
                .sign(algorithm);

        OAuthTokenResponseVO oAuthTokenResponseVO = new OAuthTokenResponseVO();
        oAuthTokenResponseVO.setAccessToken(access_token);
        oAuthTokenResponseVO.setRefreshToken(refresh_token);

        return oAuthTokenResponseVO;
    }
}
