package com.CBSEGroup11pos.registration;

import com.CBSEGroup11pos.auth.config.securityModel.ResetPasswordModel;
import com.CBSEGroup11pos.entity.User;
import com.CBSEGroup11pos.util.authUtil.RegistrationRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface RegisterService {

    ResponseEntity<User> registerUser(RegistrationRequest request);

    ResponseEntity<String> verifyEmail(String token);

    ResponseEntity<String> resendVerificationCode(String oldToken, HttpServletRequest request);

    ResponseEntity<String> resetPassword(ResetPasswordModel resetPasswordModel, HttpServletRequest request);

    ResponseEntity<String> savePassword(String token, ResetPasswordModel resetPasswordModel);
}
