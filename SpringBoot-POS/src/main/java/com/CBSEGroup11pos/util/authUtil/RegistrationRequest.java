package com.CBSEGroup11pos.util.authUtil;

public record RegistrationRequest(
        String name,
        Integer age,
        String gender,
        String address,
        String phone,
        String email,
        String role,
        String password
) {
}
