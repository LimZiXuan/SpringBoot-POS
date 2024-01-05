package com.CBSEGroup11pos.auth;

import com.CBSEGroup11pos.auth.config.securityToken.JwtAuthRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthRestImpl implements AuthRest{

    private final AuthService authenticateService;

    @Override
    public ResponseEntity<String> login(@RequestBody JwtAuthRequest request) {
        try {
            return authenticateService.login(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong!", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
