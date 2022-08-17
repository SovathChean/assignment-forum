package com.example.springassignmentforum.web.controller;

import com.example.springassignmentforum.core.service.AuthenticationService;
import com.example.springassignmentforum.web.handler.ResponseDataUtils;
import com.example.springassignmentforum.web.handler.ResponseHandler;
import com.example.springassignmentforum.web.vo.response.OAuthTokenResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;
    @GetMapping(value="/api/refreshToken")
    public ResponseEntity<ResponseDataUtils<OAuthTokenResponseVO>> getRefreshToken(HttpServletRequest request, HttpServletResponse response)
    {
        String uniqueKey = UUID.randomUUID().toString();
        OAuthTokenResponseVO token = authenticationService.refreshToken(request, response, uniqueKey);
        authenticationService.storeTokenUniqueKey(uniqueKey, false);

        return ResponseHandler.responseData(null, HttpStatus.OK, token);
    }
    @GetMapping(value="/api/logout")
    public ResponseEntity<Object> getLogout(HttpServletRequest request, HttpServletResponse response)
    {
        authenticationService.logout(request, response);

        return ResponseHandler.responseWithMsg("Logout successfully", HttpStatus.OK);
    }
}
