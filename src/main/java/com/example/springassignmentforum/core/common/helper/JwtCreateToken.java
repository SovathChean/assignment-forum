package com.example.springassignmentforum.core.common.helper;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.userdetails.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtCreateToken {

    private final Integer refreshTokenLife = 10*60*24*60*1000;//60days
    private final Integer accessTokenLife = 10*60*1000; //10min

    public Map<String, String> createTokens(HttpServletRequest request, String username)
    {
        Algorithm algorithm = JwtAlgorithm.encrptedAlgorithm();
        String access_token = JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + refreshTokenLife)) //10min
                .withIssuer(request.getRequestURL().toString())
                .sign(algorithm);
        String refresh_token = JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + accessTokenLife)) //60days
                .withIssuer(request.getRequestURL().toString())
                .sign(algorithm);
        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", access_token);
        tokens.put("refreshToken", refresh_token);
        return tokens;
    }
}
