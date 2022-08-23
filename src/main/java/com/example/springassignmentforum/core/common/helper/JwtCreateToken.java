package com.example.springassignmentforum.core.common.helper;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.springassignmentforum.web.vo.response.OAuthTokenResponseVO;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class JwtCreateToken {

    public OAuthTokenResponseVO createTokens(HttpServletRequest request, String username, String uniqueKey)
    {

        Algorithm algorithm = JwtAlgorithm.encrptedAlgorithm();
        //10min
        int accessTokenLife = 10 * 60 * 1000;
        String access_token = JWT.create()
                .withSubject(username)
                .withClaim("tokenKey", uniqueKey)
                .withExpiresAt(new Date(System.currentTimeMillis() + accessTokenLife)) //10min
                .withIssuer(request.getRequestURL().toString())
                .sign(algorithm);
        //60days
        int refreshTokenLife = 10 * 60 * 24 * 60 * 1000;
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
