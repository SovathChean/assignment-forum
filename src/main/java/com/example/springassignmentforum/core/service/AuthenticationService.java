package com.example.springassignmentforum.core.service;

import com.example.springassignmentforum.web.vo.response.OAuthTokenResponseVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface AuthenticationService {
    OAuthTokenResponseVO refreshToken(HttpServletRequest request, HttpServletResponse response, String uniqueKey);
    void storeTokenUniqueKey(String unqiueKey, Boolean isRevoke);
    Boolean isRevoke( String uniqueKey);
    void logout(HttpServletRequest request, HttpServletResponse response);
}
