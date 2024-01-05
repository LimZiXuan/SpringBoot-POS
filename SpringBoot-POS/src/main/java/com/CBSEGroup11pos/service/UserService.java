package com.CBSEGroup11pos.service;

import com.CBSEGroup11pos.auth.config.securityModel.ResetPasswordModel;
import com.CBSEGroup11pos.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface UserService {

    Optional<User> findUserByEmail(String email);

    void createPasswordResetTokenForUser(Optional<User> user, String token);

    Optional<User> getUserByPasswordResetToken(String token);

    void resetPassword(User user, String newPassword);

    ResponseEntity<String> changePassword(ResetPasswordModel resetPasswordModel);
}
