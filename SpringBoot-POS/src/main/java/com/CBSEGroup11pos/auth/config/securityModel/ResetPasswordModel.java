package com.CBSEGroup11pos.auth.config.securityModel;

import lombok.Data;

@Data
public class ResetPasswordModel {

    private String email;
    private String oldPassword;
    private String newPassword;
}
