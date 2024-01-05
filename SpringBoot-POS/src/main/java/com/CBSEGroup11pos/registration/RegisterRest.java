package com.CBSEGroup11pos.registration;

import com.CBSEGroup11pos.auth.config.securityModel.ResetPasswordModel;
import com.CBSEGroup11pos.util.authUtil.RegistrationRequest;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RequestMapping(path = "/register")
public interface RegisterRest {

    @PostMapping
    ResponseEntity<String> registerUser(@RequestBody(required = true) RegistrationRequest registrationRequest, final HttpServletRequest request);

    @GetMapping(path = "/verifyEmail")
    ResponseEntity<String> verifyEmail(@RequestParam("token") String token);

    @GetMapping(path = "/resend-verification-token")
    ResponseEntity<String> resendVerificationCode(@RequestParam("token") String oldToken, final HttpServletRequest request) throws MessagingException, UnsupportedEncodingException;

    @PostMapping(path = "/resetPassword")
    ResponseEntity<String> resetPassword(@RequestBody(required = true) ResetPasswordModel resetPasswordModel, HttpServletRequest request);

    @PostMapping(path = "/savePassword")
    ResponseEntity<String> savePassword(@RequestParam("token") String token, @RequestBody(required = true) ResetPasswordModel resetPasswordModel);
}
