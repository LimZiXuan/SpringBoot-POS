package com.CBSEGroup11pos.auth;

import com.CBSEGroup11pos.auth.config.securityToken.JwtAuthRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<String> login(JwtAuthRequest request);
}
