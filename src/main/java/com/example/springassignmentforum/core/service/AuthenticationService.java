package com.example.springassignmentforum.core.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface AuthenticationService {
    Map<String, String> refreshToken(HttpServletRequest request, HttpServletResponse response);
}
