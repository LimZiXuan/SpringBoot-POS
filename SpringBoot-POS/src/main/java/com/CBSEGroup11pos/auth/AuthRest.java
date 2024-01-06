package com.CBSEGroup11pos.auth;

import com.CBSEGroup11pos.auth.config.securityToken.JwtAuthRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "/authenticate")
public interface AuthRest {

    @PostMapping
    ResponseEntity<String> login (@RequestBody(required = true) JwtAuthRequest request);
}
