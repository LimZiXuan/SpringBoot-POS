package com.CBSEGroup11pos.auth;

import com.CBSEGroup11pos.auth.config.securityToken.JwtAuthRequest;
import com.CBSEGroup11pos.auth.config.securityToken.JwtServiceImpl;
import com.CBSEGroup11pos.entity.User;
import com.CBSEGroup11pos.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final AuthenticationManager authenticationManager;
    private final JwtServiceImpl jwtService;
    private final UserService userService;

    @Override
    public ResponseEntity<String> login(JwtAuthRequest request) {
        try {
            Authentication authentication = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    request.getEmail(),
                                    request.getPassword()
                            )
                    );
            if (authentication.isAuthenticated()) {
                Optional<User> user = userService.findUserByEmail(request.getEmail());
                if (user.get().isEnabled()) {
                    return new ResponseEntity<>("{\"token\":\"" + jwtService.getGeneratedToken(request.getEmail())
                            + "\"}", HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("{\"message\":\"" + "Waiting for admin approval." + "\"}", HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>("{\"message\":\"" + "Bad Credentials." + "\"}", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong!", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
