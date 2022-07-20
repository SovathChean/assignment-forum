//'package com.example.springassignmentforum.core.config;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//
//@Configuration
//public class AuthServerConfiguration {
//    private final PasswordEncoder passwordEncoder;
//    @Value("${user.oauth.clientId}")
//    private String CLIENT_ID;
//    @Value("${user.oauth.clientSecret}")
//    private String CLIENT_SECRET;
//    @Value("${user.oauth.grantType}")
//    private String GRANT_TYPE;
//    @Value("${user.oauth.accessTokenLifeTime}")
//    private int ACCESS_TOKEN_VALIDITY;
//    @Value("${user.oauth.refreshTokenLifeTime}")
//    private int REFRESH_TOKEN_VALIDITY;
//    @Value("${user.oauth.redirectUris}")
//    private String redirectUris;
//
//    @Autowired
//    private TokenStore tokenStore;
//
//    public AuthServerConfiguration(PasswordEncoder passwordEncoder) {
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.inMemory()
//                .withClient(CLIENT_ID)
//                .secret(passwordEncoder.encode(CLIENT_SECRET))
//                .authorizedGrantTypes("authorization_code")
//                .scopes("user_info")
//                .autoApprove(true)
//                .redirectUris(redirectUris);
//    }
//}
//'