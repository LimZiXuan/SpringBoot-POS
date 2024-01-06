package com.CBSEGroup11pos.auth.config.securityToken;

import lombok.Data;

@Data
public class JwtAuthRequest {
    private String email;
    private String password;
}
