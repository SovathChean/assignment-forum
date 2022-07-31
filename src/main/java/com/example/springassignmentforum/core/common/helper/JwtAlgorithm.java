package com.example.springassignmentforum.core.common.helper;

import com.auth0.jwt.algorithms.Algorithm;

public class JwtAlgorithm {
    private final String SECRET_KEY = "secret";

    public static Algorithm encrptedAlgorithm()
    {
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        return algorithm;
    }
}

